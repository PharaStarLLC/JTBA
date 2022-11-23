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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.Getter;
import tech.digitaldojo.jtba.data.types.*;
import tech.digitaldojo.jtba.errors.ParseError;
import tech.digitaldojo.jtba.events.EventEmitter;
import tech.digitaldojo.jtba.events.MessageEvents;
import tech.digitaldojo.jtba.events.TelegramEvents;
import tech.digitaldojo.jtba.json.JsonSerializer;
import tech.digitaldojo.jtba.polling.TelegramBotPolling;
import tech.digitaldojo.jtba.settings.TelegramBotSettings;
import tech.digitaldojo.jtba.settings.WebhookSettings;
import tech.digitaldojo.jtba.threads.PollingThread;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.http.HttpClient;
import java.util.*;
import java.util.function.Consumer;

/**
 * JTBA; tech.digitaldojo.jtba:TelegramBot
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @see <a href="https://core.telegram.org/bots/api">/bots/api</a>
 * @since 22.11.2022
 */
public class TelegramBot extends EventEmitter {

    private static TelegramBot instance;
    protected final String token;
    @Getter
    private final TelegramBotSettings settings;
    @Getter
    private User myUser;
    private HttpClient httpClient;
    private TelegramBotPolling polling;

    /**
     * Create a new instance of a telegram bot with polling enabled {@link TelegramBotPolling}
     *
     * @param token the token given by <a href="https://t.me/BotFather">@BotFather</a>
     * @see <a href="https://core.telegram.org/bots/api#authorizing-your-bot">/bots/api#authorizing-your-bot</a>
     */
    public TelegramBot(String token) {
        super();
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
        instance = this;
        this.token = token;
        this.settings = settings;
        new Thread(() -> this.init()).start();
    }


    /**
     * Initializes the telegram bot client thingy
     */
    private void init() {
        this.httpClient = HttpClient.newBuilder().build();

        if (this.settings.getBaseUrl() == null) {
            this.settings.setBaseUrl(JTBAConstants.TG_API_BASE_URL);
        }

        this.myUser = this.getMe();

        if (this.settings.isPollingEnabled()) {
            this.startPolling();
        }

        if (this.settings.getWebhookSettings() != null) {
            this.openWebhook();
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                emit(TelegramEvents.ready, myUser);
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
        this.polling.stop();
        this.polling = null;
    }

    /**
     * Opens a webhook server depending on {@link WebhookSettings} specified in {@link TelegramBotSettings}
     */
    public void openWebhook() {

    }

    private void error(Exception e) {
        JsonObject json = new JsonObject();
        json.addProperty("message", e.getMessage());
        json.addProperty("stack", String.valueOf(e.getStackTrace()));
        this.emit(TelegramEvents.error, json);
    }

    /**
     * Builds the basic api url fot bot requessts
     *
     * @param path
     * @return {@link String} url as string
     */
    public String buildUrl(String path) {
        return this.settings.getBaseUrl() + "/bot" + this.token + (this.settings.isTestEnvironment() ? "/test" : "") + path;
    }

    /**
     * Make an API request
     * {@link TelegramBot#request(String, Object)}
     *
     * @param path
     * @return {@link JsonObject} data returned from telegram api
     * @see <a href="https://core.telegram.org/bots/api#making-requests">/bots/api#making-requests</a>
     */
    public JsonElement request(String path) {
        return request(path, new Object());
    }

    /**
     * Make an API request
     *
     * @param path     API method path like '/getMe' or '/getChat'
     * @param formData any object which is serialized to form data or json depending on the request
     * @return {@link JsonObject} data returned from telegram api
     * @see <a href="https://core.telegram.org/bots/api#making-requests">/bots/api#making-requests</a>
     */
    public JsonElement request(String path, Object formData) {
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
            return JsonSerializer.fromJson(response.toString());
        } catch (Exception e) {
            error(e);
            return new JsonObject();
        }
    }

    /**
     * Resolve updates of the api
     *
     * @param formData
     * @return @return {@link PollingThread}
     * @see <a href="https://core.telegram.org/bots/api#getupdates">/bots/api#getupdates</a>
     */
    public PollingThread getUpdates(JsonObject formData) {
        return new PollingThread() {
            @Override
            public void run() {
                List<Update> updates = new ArrayList<>();
                try {
                    JsonArray array;
                    try {
                        array = instance.request("/getUpdates", formData).getAsJsonObject().get("result").getAsJsonArray();
                    } catch (Exception e) {
                        throw new ParseError("Could not parse returned data.");
                    }
                    try {
                        array.forEach(update -> updates.add(Update.fromJson(update.getAsJsonObject().toString())));
                    } catch (Exception e) {
                        throw new ParseError("Could not parse some of the returned data.");
                    }
                    if (this.getWhenDone() != null) {
                        this.getWhenDone().accept(instance, updates);
                    }
                } catch (Exception ex) {
                    if (this.getOnError() != null) {
                        this.getOnError().accept(instance, ex);
                    }
                } finally {
                    if (this.getAfterAll() != null) {
                        this.getAfterAll().accept(instance, null);
                    }
                }
            }
        };
    }

    /**
     * This method takes a update object as {@link JsonObject} and fires the specifie events
     * {@link EventEmitter#emit(MessageEvents, Message)}
     * or {@link EventEmitter#emit(TelegramEvents, Object)}
     * <br>
     * To add a listener use {@link EventEmitter#on(MessageEvents, Consumer<Message>)}
     * or {@link EventEmitter#on(TelegramEvents, Consumer<Object>)}
     *
     * @param update
     */
    public void processUpdate(Update update) {
        try {
            long now = new Date().getTime();
            Message message = update.message;
            Message editedMessage = update.edited_message;
            Message channelPost = update.channel_post;
            Message editedChannelPost = update.edited_channel_post;
            InlineQuery inlineQuery = update.inline_query;
            ChosenInlineResult chosenInlineResult = update.chosen_inline_result;
            CallbackQuery callbackQuery = update.callback_query;
            ShippingQuery shippingQuery = update.shipping_query;
            PreCheckoutQuery preCheckoutQuery = update.pre_checkout_query;
            Poll poll = update.poll;
            PollAnswer pollAnswer = update.poll_answer;
            ChatMemberUpdated chatMember = update.chat_member;
            ChatMemberUpdated myChatMember = update.my_chat_member;
            ChatJoinRequest chatJoinRequest = update.chat_join_request;
            if (message != null) {
                long secondOfAction = message.date;
                if (secondOfAction <= 0) {
                    secondOfAction = now / 1000;
                }
                if (now / 1000 - secondOfAction > 5) {
                    return; // returns if the update is older than 5 seconds
                }
                JsonObject meta = new JsonObject();
                String type = "";
                List<MessageEvents> toFire = new ArrayList<>();
                this.emit(TelegramEvents.message, message);
                for (MessageEvents messageEvents : MessageEvents.values()) {
                    if (!message.toString().contains("\"" + messageEvents + "\":")) {
                        continue;
                    }
                    toFire.add(messageEvents);
                }
                if (toFire.size() <= 0) {
                    this.emit(MessageEvents.unknown, Message.fromJson("{}"));
                    return;
                }
                toFire.forEach(e -> this.emit(e, Message.fromJson(message.toString())));
            } else if (editedMessage != null) {
                long secondOfAction = editedMessage.date;
                if (secondOfAction <= 0) {
                    secondOfAction = now / 1000;
                }
                if (now / 1000 - secondOfAction > 5) {
                    return; // returns if the update is older than 5 seconds
                }
                this.emit(TelegramEvents.edited_message, editedMessage);
                if (editedMessage.text != null) {
                    this.emit(TelegramEvents.edited_message_text, editedMessage);
                }
                if (editedMessage.caption != null) {
                    this.emit(TelegramEvents.edited_message_caption, editedMessage);
                }
            } else if (channelPost != null) {
                long secondOfAction = channelPost.date;
                if (secondOfAction <= 0) {
                    secondOfAction = now / 1000;
                }
                if (now / 1000 - secondOfAction > 5) {
                    return; // returns if the update is older than 5 seconds
                }
                this.emit(TelegramEvents.channel_post, channelPost);
            } else if (editedChannelPost != null) {
                this.emit(TelegramEvents.edited_channel_post, editedChannelPost);
                if (editedChannelPost.text != null) {
                    this.emit(TelegramEvents.edited_channel_post_text, editedChannelPost);
                }
                if (editedChannelPost.caption != null) {
                    this.emit(TelegramEvents.edited_channel_post_caption, editedChannelPost);
                }
            } else if (inlineQuery != null) {
                this.emit(TelegramEvents.inline_query, inlineQuery);
            } else if (chosenInlineResult != null) {
                this.emit(TelegramEvents.chosen_inline_result, chosenInlineResult);
            } else if (callbackQuery != null) {
                this.emit(TelegramEvents.callback_query, callbackQuery);
            } else if (shippingQuery != null) {
                this.emit(TelegramEvents.shipping_query, shippingQuery);
            } else if (preCheckoutQuery != null) {
                this.emit(TelegramEvents.pre_checkout_query, preCheckoutQuery);
            } else if (poll != null) {
                this.emit(TelegramEvents.poll, poll);
            } else if (pollAnswer != null) {
                this.emit(TelegramEvents.poll_answer, pollAnswer);
            } else if (chatMember != null) {
                this.emit(TelegramEvents.chat_member, chatMember);
            } else if (myChatMember != null) {
                this.emit(TelegramEvents.my_chat_member, myChatMember);
            } else if (chatJoinRequest != null) {
                this.emit(TelegramEvents.chat_join_request, chatJoinRequest);
            } else {
                this.emit(TelegramEvents.unknown, new JsonObject());
            }
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
        return User.fromJson(this.request("/getMe", formData).getAsJsonObject().toString());
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
     * Send text message.
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
        return Message.fromJson(this.request("/sendMessage", formData).getAsJsonObject().toString());
    }

    /**
     * Send text message.
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


}
