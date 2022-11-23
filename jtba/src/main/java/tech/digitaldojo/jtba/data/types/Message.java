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

import lombok.AllArgsConstructor;
import tech.digitaldojo.jtba.data.Data;
import tech.digitaldojo.jtba.json.JsonSerializer;

import java.util.List;

/**
 * JTBA; tech.digitaldojo.jtba.data.types:Message
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @see <a href="https://core.telegram.org/bots/api#message">/bots/api#message</a>
 * @since 23.11.2022
 */
@AllArgsConstructor
@lombok.Data
public class Message implements Data {

    public long message_id;
    public long message_thread_id;
    public User from;
    public Chat sender_chat;
    public long date;
    public Chat chat;
    public User forward_from;
    public Chat forward_from_chat;
    public long forward_from_message_id;
    public String forward_signature;
    public String forward_sender_name;
    public long forward_date;
    public boolean is_topic_message;
    public boolean is_automatic_forward;
    public Message reply_to_message;
    public User via_bot;
    public long edit_date;
    public boolean has_protected_content;
    public String media_group_id;
    public String author_signature;
    public String text;
    public List<MessageEntity> entities;
    public Animation animation;
    public Audio audio;
    public Document document;
    public List<PhotoSize> photo;
    public Video video;
    public VideoNote video_note;
    public Voice voice;
    public String caption;
    public List<MessageEntity> caption_entities;
    public Contact contact;
    public Dice dice;
    public Game game;
    public Poll poll;
    public Venue venue;
    public Location location;
    public List<User> new_chat_members;
    public User left_chat_member;
    public String new_chat_title;
    public List<PhotoSize> new_chat_photo;
    public boolean delete_chat_photo;
    public boolean group_chat_created;
    public boolean supergroup_chat_created;
    public boolean channel_chat_created;
    public MessageAutoDeleteTimerChanged message_auto_delete_timer_changed;
    public long migrate_to_chat_id;
    public long migrate_from_chat_id;
    public Message pinned_message;
    public Invoice invoice;
    public SuccessfulPayment successful_payment;
    public String connected_website;
    public PassportData passport_data;
    public ProximityAlertTriggered proximity_alert_triggered;
    public ForumTopicCreated forum_topic_created;
    public ForumTopicClosed forum_topic_closed;
    public ForumTopicReopened forum_topic_reopened;
    public VideoChatScheduled video_chat_scheduled;
    public VideoChatStarted video_chat_started;
    public VideoChatEnded video_chat_ended;
    public VideoChatParticipantsInvited video_chat_participants_invited;
    public WebAppData web_app_data;
    public InlineKeyboardMarkup reply_markup;

    public static Message fromJson(String data) {
        return JsonSerializer.fromJson(data, Message.class);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
