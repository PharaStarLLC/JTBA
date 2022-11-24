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

package tech.digitaldojo.jtba.data.types;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import tech.digitaldojo.jtba.TelegramBot;
import tech.digitaldojo.jtba.data.Data;
import tech.digitaldojo.jtba.json.JsonSerializer;

import java.util.List;

/**
 * JTBA; tech.digitaldojo.jtba.data.types:Chat
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @see <a href="https://core.telegram.org/bots/api#chat">/bots/api#chat</a>
 * @since 23.11.2022
 */
@EqualsAndHashCode
@AllArgsConstructor
public final class Chat implements Data {

    /**
     * Unique identifier for this chat. This number may have more than 32 significant bits and some programming languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a signed 64-bit integer or double-precision float type are safe for storing this identifier.
     */
    public final long id;

    /**
     * Type of chat, can be either “private”, “group”, “supergroup” or “channel”
     */
    public final String type;

    /**
     * Optional. Title, for supergroups, channels and group chats
     */
    public final String title;

    /**
     * Optional. Username, for private chats, supergroups and channels if available
     */
    public final String username;

    /**
     * Optional. First name of the other party in a private chat
     */
    public final String first_name;

    /**
     * Optional. Last name of the other party in a private chat
     */
    public final String last_name;

    /**
     * Optional. True, if the supergroup chat is a forum (has topics enabled)
     */
    public final boolean is_forum;

    /**
     * Optional. Chat photo. Returned only in getChat.
     */
    public final ChatPhoto photo;

    /**
     * Optional. If non-empty, the list of all active chat usernames; for private chats, supergroups and channels. Returned only in getChat.
     */
    public final List<String> active_usernames;

    /**
     * Optional. Custom emoji identifier of emoji status of the other party in a private chat. Returned only in getChat.
     */
    public final String emoji_status_custom_emoji_id;

    /**
     * Optional. Bio of the other party in a private chat. Returned only in getChat.
     */
    public final String bio;

    /**
     * Optional. True, if privacy settings of the other party in the private chat allows to use <code style="color: red; background-color: black;">tg://user?id={user_id}</code> links only in chats with the user. Returned only in getChat.
     */
    public final boolean has_public_forwards;

    /**
     * Optional. True, if the privacy settings of the other party restrict sending voice and video note messages in the private chat. Returned only in getChat.
     */
    public final boolean has_restricted_voice_and_video_messages;

    /**
     * Optional. True, if users need to join the supergroup before they can send messages. Returned only in getChat.
     */
    public final boolean join_to_send_messages;

    /**
     * Optional. True, if all users directly joining the supergroup need to be approved by supergroup administrators. Returned only in getChat.
     */
    public final boolean join_by_request;

    /**
     * Optional. Description, for groups, supergroups and channel chats. Returned only in getChat.
     */
    public final String description;

    /**
     * Optional. Primary invite link, for groups, supergroups and channel chats. Returned only in getChat.
     */
    public final String invite_link;

    /**
     * Optional. The most recent pinned message (by sending date). Returned only in getChat.
     */
    public final Message pinned_message;

    /**
     * Optional. Default chat member permissions, for groups and supergroups. Returned only in getChat.
     */
    public final ChatPermissions permissions;

    /**
     * Optional. For supergroups, the minimum allowed delay between consecutive messages sent by each unprivileged user; in seconds. Returned only in getChat.
     */
    public final long slow_mode_delay;

    /**
     * Optional. The time after which all messages sent to the chat will be automatically deleted; in seconds. Returned only in getChat.
     */
    public final long message_auto_delete_time;

    /**
     * Optional. True, if messages from the chat can't be forwarded to other chats. Returned only in getChat.
     */
    public final boolean has_protected_content;

    /**
     * Optional. For supergroups, name of group sticker set. Returned only in getChat.
     */
    public final String sticker_set_name;

    /**
     * Optional. True, if the bot can change the group sticker set. Returned only in getChat.
     */
    public final boolean can_set_sticker_set;

    /**
     * Optional. Unique identifier for the linked chat, i.e. the discussion group identifier for a channel and vice versa; for supergroups and channel chats. This identifier may be greater than 32 bits and some programming languages may have difficulty/silent defects in interpreting it. But it is smaller than 52 bits, so a signed 64 bit integer or double-precision float type are safe for storing this identifier. Returned only in getChat.
     */
    public final long linked_chat_id;

    /**
     * Optional. For supergroups, the location to which the supergroup is connected. Returned only in getChat.
     */
    public final ChatLocation location;


    /**
     * Creates a new {@link Chat} instance of a given JSON {@link String}.
     *
     * @param data JSON {@link String}
     * @return {@link Chat}
     */
    public static Chat fromJson(String data) {
        return JsonSerializer.fromJson(data, Chat.class);
    }

    /**
     * Convert a {@link Chat} instance to a JSON {@link String}
     *
     * @return JSON {@link String}
     */
    @Override
    public String toString() {
        return this.toJson();
    }

    /**
     * Send a message to this chat with custom formData.
     *
     * @param content  {@link String} content to send
     * @param formData the {@link JsonObject} to send as data to the api
     * @return {@link Message} the sent message
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">/bots/api#sendmessage at reply_to_message_id</a>
     */
    public Message send(String content, JsonObject formData) {
        return TelegramBot.getInstance().sendMessage(id, content, formData);
    }

    /**
     * Send a message to this chat.
     *
     * @param content {@link String} content to send
     * @return {@link Message} the sent message
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">/bots/api#sendmessage at reply_to_message_id</a>
     */
    public Message send(String content) {
        JsonObject formData = new JsonObject();
        return send(content, formData);
    }

    /**
     * Send a message to this chat with Markdown text.
     *
     * @param content {@link String} content to send
     * @return {@link Message} the sent message
     * @see <a href="https://core.telegram.org/bots/api#markdownv2-style">/bots/api#markdownv2-style</a>
     */
    public Message sendMarkdown(String content) {
        JsonObject formData = new JsonObject();
        formData.addProperty("parse_mode", "Markdown");
        return send(content, formData);
    }

    /**
     * Send a message to this chat with MarkdownV2 text.
     *
     * @param content {@link String} content to send
     * @return {@link Message} the sent message
     * @see <a href="https://core.telegram.org/bots/api#markdownv2-style">/bots/api#markdownv2-style</a>
     */
    public Message sendMarkdownV2(String content) {
        JsonObject formData = new JsonObject();
        formData.addProperty("parse_mode", "MarkdownV2");
        return send(content, formData);
    }

    /**
     * Send a message to this chat with HTML elements.
     *
     * @param content {@link String} content to send
     * @return {@link Message} the sent message
     * @see <a href="https://core.telegram.org/bots/api#html-style">/bots/api#html-style</a>
     */
    public Message sendHTML(String content) {
        JsonObject formData = new JsonObject();
        formData.addProperty("parse_mode", "HTML");
        return send(content, formData);
    }

}
