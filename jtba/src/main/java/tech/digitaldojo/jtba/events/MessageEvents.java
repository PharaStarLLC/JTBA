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

package tech.digitaldojo.jtba.events;

/**
 * JTBA; tech.digitaldojo.jtba.events:MessageEvents
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 22.11.2022
 */
public enum MessageEvents {
    text,
    animation,
    audio,
    channel_chat_created,
    contact,
    delete_chat_photo,
    dice,
    document,
    game,
    group_chat_created,
    invoice,
    left_chat_member,
    location,
    migrate_from_chat_id,
    migrate_to_chat_id,
    new_chat_members,
    new_chat_photo,
    new_chat_title,
    passport_data,
    photo,
    pinned_message,
    poll,
    sticker,
    successful_payment,
    supergroup_chat_created,
    video,
    video_note,
    voice,
    video_chat_started,
    video_chat_ended,
    video_chat_participants_invited,
    video_chat_scheduled,
    message_auto_delete_timer_changed,
    chat_invite_link,
    chat_member_updated,
    web_app_data,
    unknown;
}
