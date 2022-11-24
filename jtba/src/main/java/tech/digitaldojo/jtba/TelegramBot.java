/*
 * Copyright (c) 2022 - present | LuciferMorningstarDev <contact@lucifer-morningstar.dev>
 * Copyright (C) 2022 - present | digitaldojo.tech team and contributors
 * Copyright (C) 2022 - present | Pharaoh & Morningstar LLC team and contributors
 *
 * JTBA - Java Telegram Bot API - Developed with ♥ and Published by digitaldojo.tech
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package tech.digitaldojo.jtba;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;
import tech.digitaldojo.jtba.data.types.Message;
import tech.digitaldojo.jtba.data.types.Update;
import tech.digitaldojo.jtba.data.types.Updates;
import tech.digitaldojo.jtba.data.types.User;
import tech.digitaldojo.jtba.events.EventEmitter;
import tech.digitaldojo.jtba.events.MessageEvents;
import tech.digitaldojo.jtba.events.TelegramEvents;
import tech.digitaldojo.jtba.json.JsonSerializer;
import tech.digitaldojo.jtba.polling.TelegramBotPolling;
import tech.digitaldojo.jtba.settings.TelegramBotSettings;
import tech.digitaldojo.jtba.settings.WebhookSettings;
import tech.digitaldojo.jtba.webhook.TelegramBotWebHook;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

/**
 * JTBA; tech.digitaldojo.jtba:TelegramBot
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @see <a href="https://core.telegram.org/bots/api">/bots/api</a>
 * @since 22.11.2022
 */
public class TelegramBot extends EventEmitter {

    @Getter
    private static TelegramBot instance;
    protected final String token;
    @Getter
    private final TelegramBotSettings settings;
    @Getter
    private User myUser;
    private HttpClient httpClient;
    private TelegramBotPolling polling;
    private TelegramBotWebHook webhook;

    /**
     * Create a new instance of a telegram bot with polling enabled {@link TelegramBotPolling}
     *
     * @param token the token given by <a href="https://t.me/BotFather">@BotFather</a>
     * @see <a href="https://core.telegram.org/bots/api#authorizing-your-bot">/bots/api#authorizing-your-bot</a>
     */
    public TelegramBot(String token) {
        super();
        if (instance != null) {
            throw new RuntimeException("TelegramBot can only instantiated once.");
        }
        instance = this;
        this.token = token;
        this.settings = new TelegramBotSettings(false, true, 300, JTBAConstants.TG_API_BASE_URL, null, null);
        new Thread(() -> this.init()).start();
    }

    /**
     * Create a new instance of a telegram bot with  custom settings ( You can enable webhooks or polling for the bot to receive updates )
     * <br /><a href="https://core.telegram.org/bots/api#getting-updates">/bots/api#getting-updates</a>
     *
     * @param token    the token given by <a href="https://t.me/BotFather">@BotFather</a>
     * @param settings the {@link TelegramBotSettings}
     * @see <a href="https://core.telegram.org/bots/api#authorizing-your-bot">/bots/api#authorizing-your-bot</a>
     */
    public TelegramBot(String token, TelegramBotSettings settings) {
        super();
        if (instance != null) {
            throw new RuntimeException("TelegramBot can only instantiated once.");
        }
        instance = this;
        this.token = token;
        this.settings = settings;
        new Thread(() -> this.init()).start();
    }

    /**
     * Create a new instance of a telegram bot with webhook enabled {@link TelegramBotWebHook}
     *
     * @param token    the token given by <a href="https://t.me/BotFather">@BotFather</a>
     * @param settings the {@link WebhookSettings}
     */
    public TelegramBot(String token, WebhookSettings settings) {
        super();
        if (instance != null) {
            throw new RuntimeException("TelegramBot can only instantiated once.");
        }
        instance = this;
        this.token = token;
        this.settings = new TelegramBotSettings(false, false, 300, JTBAConstants.TG_API_BASE_URL, null, settings);
        new Thread(() -> this.init()).start();
    }


    /**
     * Initializes the telegram bot client thingy
     */
    private void init() {
        this.httpClient = HttpClient.newBuilder().build();

        this.myUser = this.getMe();

        if (this.settings.pollingEnabled) {
            this.startPolling();
        }

        if (this.settings.webhookSettings != null) {
            this.openWebhook();
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                emit(TelegramEvents.ready, Update.fromJson("{\"me\": " + myUser + "}"));
            }
        }, 50);
    }

    /**
     * start telegram update polling
     */
    public void startPolling() {
        this.polling = new TelegramBotPolling(this);
        this.polling.start();
    }

    /**
     * stop telegram update polling
     */
    public void stopPolling() {
        if (this.polling != null) {
            this.polling.stop();
        }
        this.polling = null;
    }

    /**
     * Opens a webhook server depending on {@link WebhookSettings} specified in {@link TelegramBotSettings}
     */
    public void openWebhook() {
        this.webhook = new TelegramBotWebHook(this);
        this.webhook.start();
    }

    public void closeWebhook() {
        if (this.webhook != null) {
            this.webhook.stop();
        }
        this.webhook = null;
    }

    private void error(Exception e) {
        this.emit(TelegramEvents.error, Update.fromJson("{\"error\": true}"));
    }

    /**
     * Builds the basic api url fot bot requessts
     *
     * @param path
     * @return {@link String} url as string
     */
    public String buildUrl(String path) {
        return this.settings.baseUrl + "/bot" + this.token + (this.settings.testEnvironment ? "/test" : "") + path;
    }

    /**
     * Make an API request
     * {@link TelegramBot#request(String, Object)}
     *
     * @param path
     * @return {@link String} JSON data returned from telegram api
     * @see <a href="https://core.telegram.org/bots/api#making-requests">/bots/api#making-requests</a>
     */
    public String request(String path) {
        return request(path, new Object());
    }

    /**
     * Make an API request
     *
     * @param path     API method path like '/getMe' or '/getChat'
     * @param formData any object which is serialized to form data or json depending on the request
     * @return {@link String} JSON data returned from telegram api
     * @see <a href="https://core.telegram.org/bots/api#making-requests">/bots/api#making-requests</a>
     */
    public String request(String path, Object formData) {
        try {
            if (formData == null) {
                formData = new Object();
            }
            String body = null;
            if (formData instanceof JsonObject) {
                body = formData.toString();
            } else if (formData instanceof JsonArray) {
                body = formData.toString();
            } else {
                JsonSerializer.toJson(formData);
                if (body == null) {
                    body = JsonSerializer.toJson(new Object());
                }
            }

            String _url = this.buildUrl(path);
            URL url = new URL(_url);
            // System.out.println("Request(URL(" + _url + "),Body(\"" + body + "\"))");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            final DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(body);
            out.flush();
            out.close();
            //Reading the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inLine;
            while ((inLine = in.readLine()) != null) {
                response.append(inLine);
            }
            in.close();
            connection.getResponseCode();
            connection.disconnect();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    /**
     * Resolve updates of the api
     *
     * @param formData
     * @return {@link Updates}
     * @see <a href="https://core.telegram.org/bots/api#getupdates">/bots/api#getupdates</a>
     */
    public Updates getUpdates(JsonObject formData) {
        return Updates.fromJson(this.request("", formData));
    }

    /**
     * This method takes a update object as {@link JsonObject} and fires the specifie events
     * {@link EventEmitter#emit(MessageEvents, Message)}
     * or {@link EventEmitter#emit(TelegramEvents, Update)}
     * <br>
     * To add a listener use {@link EventEmitter#on(MessageEvents, Consumer<Message>)}
     * or {@link EventEmitter#on(TelegramEvents, Consumer<Update>)}
     *
     * @param update
     */
    public void processUpdate(Update update) {
        try {
            long now = new Date().getTime();

            if (update.message != null) {
                long secondOfAction = update.message.date;
                if (secondOfAction <= 0) {
                    secondOfAction = now / 1000;
                }
                if (now / 1000 - secondOfAction > 5) {
                    return; // returns if the update is older than 5 seconds
                }
                JsonObject meta = new JsonObject();
                String type = "";
                List<MessageEvents> toFire = new ArrayList<>();
                this.emit(TelegramEvents.message, update);
                for (MessageEvents messageEvents : MessageEvents.values()) {
                    if (!update.message.toString().contains("\"" + messageEvents + "\":")) {
                        continue;
                    }
                    toFire.add(messageEvents);
                }
                if (toFire.size() <= 0) {
                    this.emit(MessageEvents.unknown, Message.fromJson("{}"));
                    return;
                }
                toFire.forEach(e -> this.emit(e, update.message));
                return;
            }

            if (update.edited_message != null) {
                long secondOfAction = update.edited_message.date;
                if (secondOfAction <= 0) {
                    secondOfAction = now / 1000;
                }
                if (now / 1000 - secondOfAction > 5) {
                    return; // returns if the update is older than 5 seconds
                }
                this.emit(TelegramEvents.edited_message, update);
                if (update.edited_message.text != null) {
                    this.emit(TelegramEvents.edited_message_text, update);
                }
                if (update.edited_message.caption != null) {
                    this.emit(TelegramEvents.edited_message_caption, update);
                }
                return;
            }

            if (update.channel_post != null) {
                long secondOfAction = update.channel_post.date;
                if (secondOfAction <= 0) {
                    secondOfAction = now / 1000;
                }
                if (now / 1000 - secondOfAction > 5) {
                    return; // returns if the update is older than 5 seconds
                }
                this.emit(TelegramEvents.channel_post, update);
                return;
            }

            if (update.edited_channel_post != null) {
                this.emit(TelegramEvents.edited_channel_post, update);
                if (update.edited_channel_post.text != null) {
                    this.emit(TelegramEvents.edited_channel_post_text, update);
                }
                if (update.edited_channel_post.caption != null) {
                    this.emit(TelegramEvents.edited_channel_post_caption, update);
                }
                return;
            }

            if (update.inline_query != null) {
                this.emit(TelegramEvents.inline_query, update);
                return;
            }

            if (update.chosen_inline_result != null) {
                this.emit(TelegramEvents.chosen_inline_result, update);
                return;
            }

            if (update.callback_query != null) {
                this.emit(TelegramEvents.callback_query, update);
                return;
            }

            if (update.shipping_query != null) {
                this.emit(TelegramEvents.shipping_query, update);
                return;
            }

            if (update.pre_checkout_query != null) {
                this.emit(TelegramEvents.pre_checkout_query, update);
                return;
            }

            if (update.poll != null) {
                this.emit(TelegramEvents.poll, update);
                return;
            }

            if (update.poll_answer != null) {
                this.emit(TelegramEvents.poll_answer, update);
                return;
            }

            if (update.chat_member != null) {
                this.emit(TelegramEvents.chat_member, update);
                return;
            }

            if (update.my_chat_member != null) {
                this.emit(TelegramEvents.my_chat_member, update);
                return;
            }

            if (update.chat_join_request != null) {
                this.emit(TelegramEvents.chat_join_request, update);
                return;
            }

            this.emit(TelegramEvents.unknown, update);

        } catch (Exception e) {
            error(e);
        }
    }

    /**
     * Resolve the current bot data
     *
     * @param formData
     * @return {@link User} bot data returned from telegram api
     * @see <a href="https://core.telegram.org/bots/api#getme">/bots/api#getme</a>
     */
    public User getMe(JsonObject formData) {
        return User.fromJson(this.request("/getMe", formData));
    }

    /**
     * Resolve the current bot data
     * {@link TelegramBot#getMe(JsonObject)}
     *
     * @return {@link User} bot data returned from telegram api
     * @see <a href="https://core.telegram.org/bots/api#getme">/bots/api#getme</a>
     */
    public User getMe() {
        return getMe(new JsonObject());
    }

    /**
     * Use this method to send text messages. On success, the sent Message is returned.
     *
     * @param chatId   the id if the chat to send the message in
     * @param message  the content of the message
     * @param formData optional formData for buttons parse mode and uch things
     * @return {@link Message} sent message data returned from telegram api
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">/bots/api#sendmessage</a>
     */
    public Message sendMessage(long chatId, String message, JsonObject formData) {
        formData.addProperty("chat_id", chatId);
        formData.addProperty("text", message);
        return Message.fromJson(this.request("/sendMessage", formData));
    }

    /**
     * Use this method to send text messages. On success, the sent Message is returned.
     * {@link TelegramBot#sendMessage(long, String, JsonObject)}
     *
     * @param chatId  the id if the chat to send the message in
     * @param message the content of the message
     * @return {@link Message} sent message data returned from telegram api
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">/bots/api#sendmessage</a>
     */
    public Message sendMessage(long chatId, String message) {
        return sendMessage(chatId, message, new JsonObject());
    }

    /**
     * Use this method to log out from the cloud Bot API server before
     * launching the bot locally. You must log out the bot before
     * running it locally, otherwise there is no guarantee that the bot
     * will receive updates.
     * <br />
     * After a successful call, you can immediately log in on a local
     * server, but will not be able to log in back to the cloud Bot API
     * server for 10 minutes.
     * <br />
     * Returns True on success. Requires no parameters.
     *
     * @return success
     */
    public boolean logOut() {
        return this.request("/logOut") != null;
    }

    /**
     * Use this method to close the bot instance before moving it from
     * one local server to another. You need to delete the webhook
     * before calling this method to ensure that the bot isn't
     * launched again after server restart.
     * <br />
     * The method will return error 429 in the first 10 minutes after
     * the bot is launched.
     * <br />
     * Returns True on success. Requires no parameters.
     *
     * @return success
     */
    public boolean close() {
        return this.request("/close") != null;
    }

    /**
     * Use this method to forward messages of any kind. Service messages can't be forwarded. On success, the sent Message is returned.
     *
     * @param chat_id              Unique identifier for the target chat
     * @param from_chat_id         Unique identifier for the chat where the original message was sent
     * @param message_id           Message identifier in the chat specified in from_chat_id
     * @param disable_notification Sends the message silently. Users will receive a notification with no sound.
     * @param protect_content      Protects the contents of the forwarded message from forwarding and saving
     * @return {@link Message}
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">/bots/api#forwardmessage</a>
     */
    public Message forwardMessage(long chat_id, long from_chat_id, long message_id, boolean disable_notification, boolean protect_content) {
        JsonObject formData = new JsonObject();
        formData.addProperty("chat_id", chat_id);
        formData.addProperty("from_chat_id", from_chat_id);
        formData.addProperty("message_id", message_id);
        formData.addProperty("disable_notification", disable_notification);
        formData.addProperty("protect_content", protect_content);
        return Message.fromJson(this.request("/forwardMessage", formData));
    }

    /**
     * Use this method to forward messages of any kind. Service messages can't be forwarded. On success, the sent Message is returned.
     *
     * @param message The message to forward
     * @param chat_id Unique identifier for the target chat
     * @return {@link Message}
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">/bots/api#forwardmessage</a>
     */
    public Message forwardMessage(Message message, long chat_id) {
        return forwardMessage(chat_id, message.chat.id, message.message_id, false, false);
    }

    /**
     * Use this method to forward messages of any kind. Service messages can't be forwarded. On success, the sent Message is returned.
     *
     * @param message              The message to forward
     * @param chat_id              Unique identifier for the target chat
     * @param disable_notification Sends the message silently. Users will receive a notification with no sound.
     * @param protect_content      Protects the contents of the forwarded message from forwarding and saving
     * @return {@link Message}
     * @see <a href="https://core.telegram.org/bots/api#forwardmessage">/bots/api#forwardmessage</a>
     */
    public Message forwardMessage(Message message, long chat_id, boolean disable_notification, boolean protect_content) {
        return forwardMessage(chat_id, message.chat.id, message.message_id, disable_notification, protect_content);
    }


}
