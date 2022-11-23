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

import lombok.Getter;
import lombok.Setter;
import tech.digitaldojo.jtba.json.JsonSerializer;

/**
 * JTBA; tech.digitaldojo.jtba.data.types:ChatMemberRestricted
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @see <a href="https://core.telegram.org/bots/api#chatmemberrestricted">/bots/api#chatmemberrestricted</a>
 * @since 23.11.2022
 */
@Getter
@Setter
public class ChatMemberRestricted extends ChatMember {

    public static ChatMemberType type = ChatMemberType.RESTRICTED;

    public long until_date;

    public boolean is_member;
    public boolean can_change_info;
    public boolean can_invite_users;
    public boolean can_pin_messages;
    public boolean can_manage_topics;
    public boolean can_send_messages;
    public boolean can_send_media_messages;
    public boolean can_send_polls;
    public boolean can_send_other_messages;
    public boolean can_add_web_page_previews;


    public ChatMemberRestricted(
            final User user,
            final String status,
            final long until_date,
            final boolean is_member,
            final boolean can_change_info,
            final boolean can_invite_users,
            final boolean can_pin_messages,
            final boolean can_manage_topics,
            final boolean can_send_messages,
            final boolean can_send_media_messages,
            final boolean can_send_polls,
            final boolean can_send_other_messages,
            final boolean can_add_web_page_previews
    ) {
        super(user, status);
        this.until_date = until_date;
        this.is_member = is_member;
        this.can_change_info = can_change_info;
        this.can_invite_users = can_invite_users;
        this.can_pin_messages = can_pin_messages;
        this.can_manage_topics = can_manage_topics;
        this.can_send_messages = can_send_messages;
        this.can_send_media_messages = can_send_media_messages;
        this.can_send_polls = can_send_polls;
        this.can_send_other_messages = can_send_other_messages;
        this.can_add_web_page_previews = can_add_web_page_previews;
    }

    public static ChatMemberRestricted fromJson(String data) {
        return JsonSerializer.fromJson(data, ChatMemberRestricted.class);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
