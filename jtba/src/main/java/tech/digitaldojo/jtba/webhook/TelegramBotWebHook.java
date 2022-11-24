package tech.digitaldojo.jtba.webhook;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import tech.digitaldojo.jtba.TelegramBot;
import tech.digitaldojo.jtba.data.types.Update;
import tech.digitaldojo.jtba.errors.FatalError;
import tech.digitaldojo.jtba.errors.ParseError;
import tech.digitaldojo.jtba.events.TelegramEvents;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetSocketAddress;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * JTBA; tech.digitaldojo.jtba.webhook:TelegramBotWebHook
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 24.11.2022
 */
public class TelegramBotWebHook {

    private final TelegramBot telegramBot;
    private Thread webhookThread;

    public TelegramBotWebHook(final TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void start() {
        if (telegramBot.getSettings().getWebhookSettings().getSecretToken().length() < 64) {
            error(new FatalError("Secret token must have a minimum length of 64 characters."));
            return;
        }
        if (telegramBot.getSettings().getWebhookSettings().getSecretToken().length() > 256) {
            error(new FatalError("Secret token must have a maximum length of 256 characters."));
            return;
        }
        if (webhookThread != null) {
            if (webhookThread.isAlive()) {
                webhookThread.interrupt();
            }
        }
        this.webhookThread = new Thread(() -> {
            try {
                boolean secure = telegramBot.getSettings().getWebhookSettings().isSecure();
                URL webhookUrl = new URL(telegramBot.getSettings().getWebhookSettings().getBaseUrl());
                if (secure) {
                    HttpsServer server = HttpsServer.create(new InetSocketAddress(telegramBot.getSettings().getWebhookSettings().getHost(), telegramBot.getSettings().getWebhookSettings().getPort()), 0);
                    InputStream is = new FileInputStream(telegramBot.getSettings().getWebhookSettings().getSslCertificatePath());
                    CertificateFactory cf = CertificateFactory.getInstance("X.509");
                    X509Certificate caCert = (X509Certificate) cf.generateCertificate(is);
                    TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
                    ks.load(null);
                    ks.setCertificateEntry("caCert", caCert);
                    tmf.init(ks);
                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(null, tmf.getTrustManagers(), null);
                    server.setHttpsConfigurator(new HttpsConfigurator(sslContext));
                    server.createContext(webhookUrl.getPath(), new WebhookHander());
                    server.createContext("/", new NotFoundHander());
                    server.setExecutor(null); // creates a default executor
                    server.start();
                    System.out.println(String.format("HTTPS Webhook Opened on %s:%s public URL: %s", telegramBot.getSettings().getWebhookSettings().getHost(), telegramBot.getSettings().getWebhookSettings().getPort(), telegramBot.getSettings().getWebhookSettings().getBaseUrl()));
                } else {
                    HttpServer server = HttpServer.create(new InetSocketAddress(telegramBot.getSettings().getWebhookSettings().getHost(), telegramBot.getSettings().getWebhookSettings().getPort()), 0);
                    server.createContext(webhookUrl.getPath(), new WebhookHander());
                    server.createContext("/", new NotFoundHander());
                    server.setExecutor(null); // creates a default executor
                    server.start();
                    System.out.println(String.format("HTTP Webhook Opened on %s:%s public URL: %s", telegramBot.getSettings().getWebhookSettings().getHost(), telegramBot.getSettings().getWebhookSettings().getPort(), telegramBot.getSettings().getWebhookSettings().getBaseUrl()));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }, "TelegramBotWebHook");
        this.webhookThread.start();
    }

    private void restart() {
        this.stop();
        this.start();
    }

    public void stop() {
        if (webhookThread != null) {
            this.webhookThread.interrupt();
            webhookThread = null;
        }
    }

    class WebhookHander implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().add("Content-Type", "application/json");

            if (exchange.getRequestMethod().toLowerCase() != "post") {
                String response = "{\"error\":true,\"message\":\"forbidden\"}";
                exchange.sendResponseHeaders(403, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                return;
            }

            String secret = telegramBot.getSettings().getWebhookSettings().getSecretToken();
            String headerSecret = exchange.getRequestHeaders().getFirst("X-Telegram-Bot-Api-Secret-Token");
            if (headerSecret == null) {
                headerSecret = exchange.getRequestHeaders().getFirst("x-telegram-bot-api-secret-token");
            }

            if (!secret.equals(headerSecret)) {
                String response = "{\"error\":true,\"message\":\"unauthorized\"}";
                exchange.sendResponseHeaders(401, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
                return;
            }

            // Read incoming data
            InputStream comingData = exchange.getRequestBody();
            StringBuilder bodyBuilder = new StringBuilder();
            try (Reader reader = new BufferedReader(new InputStreamReader(comingData, "UTF-8"))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    bodyBuilder.append((char) c);
                }
            }

            Update update = Update.fromJson(bodyBuilder.toString());
            if (update == null) {
                try {
                    throw new ParseError("Could not parse incoming webhook data...");
                } catch (ParseError e) {
                    error(e);
                }
            }

            String response = "{\"error\":false,\"message\":\"OK\"}";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

            new Thread(() -> telegramBot.processUpdate(update), "update-" + update.update_id).start();
        }

    }

    class NotFoundHander implements HttpHandler {

        @Override
        public void handle(final HttpExchange exchange) throws IOException {
            exchange.getResponseHeaders().add("Content-Type", "application/json");

            String response = "{\"error\":true,\"message\":\"unknown\"}";
            exchange.sendResponseHeaders(404, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

    }

    private void error(Exception e) {
        JsonObject json = new JsonObject();
        json.addProperty("error", e.getMessage());
        e.printStackTrace();
        this.telegramBot.emit(TelegramEvents.polling_error, json);
    }

}
