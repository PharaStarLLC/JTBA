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
import tech.digitaldojo.jtba.TelegramBot;
import tech.digitaldojo.jtba.data.Data;
import tech.digitaldojo.jtba.json.JsonSerializer;

import java.util.List;

/**
 * JTBA; tech.digitaldojo.jtba.data.types:Message
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @see <a href="https://core.telegram.org/bots/api#message">/bots/api#message</a>
 * @since 23.11.2022
 */
@AllArgsConstructor
@lombok.Data
public class Message implements Data {

    /**
     * Unique message identifier inside this chat
     */
    public long message_id;

    /**
     * Optional. Unique identifier of a message thread to which the message belongs; for supergroups only
     */
    public long message_thread_id;

    /**
     * Optional. Sender of the message; empty for messages sent to channels. For backward compatibility, the field contains a fake sender user in non-channel chats, if the message was sent on behalf of a chat.
     */
    public User from;

    /**
     * Optional. Sender of the message, sent on behalf of a chat. For example, the channel itself for channel posts, the supergroup itself for messages from anonymous group administrators, the linked channel for messages automatically forwarded to the discussion group. For backward compatibility, the field from contains a fake sender user in non-channel chats, if the message was sent on behalf of a chat.
     */
    public Chat sender_chat;

    /**
     * Date the message was sent in Unix time
     */
    public long date;

    /**
     * Conversation the message belongs to
     */
    public Chat chat;

    /**
     * Optional. For forwarded messages, sender of the original message
     */
    public User forward_from;

    /**
     * Optional. For messages forwarded from channels or from anonymous administrators, information about the original sender chat
     */
    public Chat forward_from_chat;

    /**
     * Optional. For messages forwarded from channels, identifier of the original message in the channel
     */
    public long forward_from_message_id;

    /**
     * Optional. For forwarded messages that were originally sent in channels or by an anonymous chat administrator, signature of the message sender if present
     */
    public String forward_signature;

    /**
     * Optional. Sender's name for messages forwarded from users who disallow adding a link to their account in forwarded messages
     */
    public String forward_sender_name;

    /**
     * Optional. For forwarded messages, date the original message was sent in Unix time
     */
    public long forward_date;

    /**
     * Optional. True, if the message is sent to a forum topic
     */
    public boolean is_topic_message;

    /**
     * Optional. True, if the message is a channel post that was automatically forwarded to the connected discussion group
     */
    public boolean is_automatic_forward;

    /**
     * Optional. For replies, the original message. Note that the Message object in this field will not contain further reply_to_message fields even if it itself is a reply.
     */
    public Message reply_to_message;

    /**
     * Optional. Bot through which the message was sent
     */
    public User via_bot;

    /**
     * Optional. Date the message was last edited in Unix time
     */
    public long edit_date;

    /**
     * Optional. True, if the message can't be forwarded
     */
    public boolean has_protected_content;

    /**
     * Optional. The unique identifier of a media message group this message belongs to
     */
    public String media_group_id;

    /**
     * Optional. Signature of the post author for messages in channels, or the custom title of an anonymous group administrator
     */
    public String author_signature;

    /**
     * Optional. For text messages, the actual UTF-8 text of the message
     */
    public String text;

    /**
     * Optional. For text messages, special entities like usernames, URLs, bot commands, etc. that appear in the text
     */
    public List<MessageEntity> entities;

    /**
     * Optional. Message is an animation, information about the animation. For backward compatibility, when this field is set, the document field will also be set
     */
    public Animation animation;

    /**
     * Optional. Message is an audio file, information about the file
     */
    public Audio audio;

    /**
     * Optional. Message is a general file, information about the file
     */
    public Document document;

    /**
     * Optional. Message is a photo, available sizes of the photo
     */
    public List<PhotoSize> photo;

    /**
     * Optional. Message is a sticker, information about the sticker
     */
    public Sticker sticker;

    /**
     * Optional. Message is a video, information about the video
     */
    public Video video;

    /**
     * Optional. Message is a {@link VideoNote video note}, information about the video message
     */
    public VideoNote video_note;

    /**
     * Optional. Message is a voice message, information about the file
     */
    public Voice voice;

    /**
     * Optional. Caption for the animation, audio, document, photo, video or voice
     */
    public String caption;

    /**
     * Optional. For messages with a caption, special entities like usernames, URLs, bot commands, etc. that appear in the caption
     */
    public List<MessageEntity> caption_entities;

    /**
     * Optional. Message is a shared contact, information about the contact
     */
    public Contact contact;

    /**
     * Optional. Message is a dice with random value
     */
    public Dice dice;

    /**
     * Optional. Message is a game, information about the game. <a href="https://core.telegram.org/bots/api#games">More about games</a>
     */
    public Game game;

    /**
     * Optional. Message is a native poll, information about the poll
     */
    public Poll poll;

    /**
     * Optional. Message is a venue, information about the venue. For backward compatibility, when this field is set, the location field will also be set
     */
    public Venue venue;

    /**
     * Optional. Message is a shared location, information about the location
     */
    public Location location;

    /**
     * Optional. New members that were added to the group or supergroup and information about them (the bot itself may be one of these members)
     */
    public List<User> new_chat_members;

    /**
     * Optional. A member was removed from the group, information about them (this member may be the bot itself)
     */
    public User left_chat_member;

    /**
     * Optional. A chat title was changed to this value
     */
    public String new_chat_title;

    /**
     * Optional. A chat photo was change to this value
     */
    public List<PhotoSize> new_chat_photo;

    /**
     * Optional. Service message: the chat photo was deleted
     */
    public boolean delete_chat_photo;

    /**
     * Optional. Service message: the group has been created
     */
    public boolean group_chat_created;

    /**
     * Optional. Service message: the supergroup has been created. This field can't be received in a message coming through updates, because bot can't be a member of a supergroup when it is created. It can only be found in reply_to_message if someone replies to a very first message in a directly created supergroup.
     */
    public boolean supergroup_chat_created;

    /**
     * Optional. Service message: the channel has been created. This field can't be received in a message coming through updates, because bot can't be a member of a channel when it is created. It can only be found in reply_to_message if someone replies to a very first message in a channel.
     */
    public boolean channel_chat_created;

    /**
     * Optional. Service message: auto-delete timer settings changed in the chat
     */
    public MessageAutoDeleteTimerChanged message_auto_delete_timer_changed;

    /**
     * Optional. The group has been migrated to a supergroup with the specified identifier. This number may have more than 32 significant bits and some programming languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a signed 64-bit integer or double-precision float type are safe for storing this identifier.
     */
    public long migrate_to_chat_id;

    /**
     * Optional. The supergroup has been migrated from a group with the specified identifier. This number may have more than 32 significant bits and some programming languages may have difficulty/silent defects in interpreting it. But it has at most 52 significant bits, so a signed 64-bit integer or double-precision float type are safe for storing this identifier.
     */
    public long migrate_from_chat_id;

    /**
     * Optional. Specified message was pinned. Note that the Message object in this field will not contain further reply_to_message fields even if it is itself a reply.
     */
    public Message pinned_message;

    /**
     * Optional. Message is an invoice for a payment, information about the invoice. <a href="https://core.telegram.org/bots/api#payments">More about payments</a>
     */
    public Invoice invoice;

    /**
     * Optional. Message is a service message about a successful payment, information about the payment. <a href="https://core.telegram.org/bots/api#payments">More about payments</a>
     */
    public SuccessfulPayment successful_payment;

    /**
     * Optional. The domain name of the website on which the user has logged in. <a href="https://core.telegram.org/widgets/login">More about Telegram Login</a>
     */
    public String connected_website;

    /**
     * Optional. Telegram Passport data
     */
    public PassportData passport_data;

    /**
     * Optional. Service message. A user in the chat triggered another user's proximity alert while sharing Live Location.
     */
    public ProximityAlertTriggered proximity_alert_triggered;

    /**
     * Optional. Service message: forum topic created
     */
    public ForumTopicCreated forum_topic_created;

    /**
     * Optional. Service message: forum topic closed
     */
    public ForumTopicClosed forum_topic_closed;

    /**
     * Optional. Service message: forum topic reopened
     */
    public ForumTopicReopened forum_topic_reopened;

    /**
     * Optional. Service message: video chat scheduled
     */
    public VideoChatScheduled video_chat_scheduled;

    /**
     * Optional. Service message: video chat started
     */
    public VideoChatStarted video_chat_started;

    /**
     * Optional. Service message: video chat ended
     */
    public VideoChatEnded video_chat_ended;

    /**
     * Optional. Service message: new participants invited to a video chat
     */
    public VideoChatParticipantsInvited video_chat_participants_invited;

    /**
     * Optional. Service message: data sent by a Web App
     */
    public WebAppData web_app_data;

    /**
     * Optional. Inline keyboard attached to the message. login_url buttons are represented as ordinary url buttons.
     */
    public InlineKeyboardMarkup reply_markup;

    /**
     * Creates a new {@link Message} instance of a given JSON {@link String}.
     *
     * @param data JSON {@link String}
     * @return {@link Message}
     */
    public static Message fromJson(String data) {
        return JsonSerializer.fromJson(data, Message.class);
    }

    /**
     * Convert a {@link Message} instance to a JSON {@link String}
     *
     * @return JSON {@link String}
     */
    @Override
    public String toString() {
        return this.toJson();
    }

    /**
     * Reply to this message with custom formData.
     *
     * @param content  {@link String} content to send
     * @param formData the {@link JsonObject} to send as data to the api
     * @return {@link Message} the sent message
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">/bots/api#sendmessage at reply_to_message_id</a>
     */
    public Message reply(String content, JsonObject formData) {
        formData.addProperty("reply_to_message_id", message_id);
        return TelegramBot.getInstance().sendMessage(chat.id, content, formData);
    }

    /**
     * Reply to this message.
     *
     * @param content {@link String} content to send
     * @return {@link Message} the sent message
     * @see <a href="https://core.telegram.org/bots/api#sendmessage">/bots/api#sendmessage at reply_to_message_id</a>
     */
    public Message reply(String content) {
        JsonObject formData = new JsonObject();
        formData.addProperty("reply_to_message_id", message_id);
        return reply(content, formData);
    }

    /**
     * Reply to this message with Markdown text.
     *
     * @param content {@link String} content to send
     * @return {@link Message} the sent message
     * @see <a href="https://core.telegram.org/bots/api#markdownv2-style">/bots/api#markdownv2-style</a>
     */
    public Message replyMarkdown(String content) {
        JsonObject formData = new JsonObject();
        formData.addProperty("reply_to_message_id", message_id);
        formData.addProperty("parse_mode", "Markdown");
        return reply(content, formData);
    }

    /**
     * Reply to this message with MarkdownV2 text.
     *
     * @param content {@link String} content to send
     * @return {@link Message} the sent message
     * @see <a href="https://core.telegram.org/bots/api#markdownv2-style">/bots/api#markdownv2-style</a>
     */
    public Message replyMarkdownV2(String content) {
        JsonObject formData = new JsonObject();
        formData.addProperty("reply_to_message_id", message_id);
        formData.addProperty("parse_mode", "MarkdownV2");
        return reply(content, formData);
    }

    /**
     * Reply to this message with HTML elements.
     *
     * @param content {@link String} content to send
     * @return {@link Message} the sent message
     * @see <a href="https://core.telegram.org/bots/api#html-style">/bots/api#html-style</a>
     */
    public Message replyHTML(String content) {
        JsonObject formData = new JsonObject();
        formData.addProperty("reply_to_message_id", message_id);
        formData.addProperty("parse_mode", "HTML");
        return reply(content, formData);
    }

}
