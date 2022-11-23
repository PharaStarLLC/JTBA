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
 * JTBA; tech.digitaldojo.jtba.data.types:ChatMemberAdministrator
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @see <a href="https://core.telegram.org/bots/api#chatmemberadministrator">/bots/api#chatmemberadministrator</a>
 * @since 23.11.2022
 */
@Getter
@Setter
public class ChatMemberAdministrator extends ChatMemberOwner {

    public static ChatMemberType type = ChatMemberType.ADMINISTRATOR;

    public boolean can_be_edited;
    public boolean can_manage_chat;
    public boolean can_delete_messages;
    public boolean can_manage_video_chats;
    public boolean can_restrict_members;
    public boolean can_promote_members;
    public boolean can_change_info;
    public boolean can_invite_users;
    public boolean can_post_messages;
    public boolean can_edit_messages;
    public boolean can_pin_messages;
    public boolean can_manage_topics;

    public ChatMemberAdministrator(
            final User user,
            final String status,
            final boolean is_anonymous,
            final String custom_title,
            final boolean can_be_edited,
            final boolean can_manage_chat,
            final boolean can_delete_messages,
            final boolean can_manage_video_chats,
            final boolean can_restrict_members,
            final boolean can_promote_members,
            final boolean can_change_info,
            final boolean can_invite_users,
            final boolean can_post_messages,
            final boolean can_edit_messages,
            final boolean can_pin_messages,
            final boolean can_manage_topics
    ) {
        super(user, status, is_anonymous, custom_title);
        this.can_be_edited = can_be_edited;
        this.can_manage_chat = can_manage_chat;
        this.can_delete_messages = can_delete_messages;
        this.can_manage_video_chats = can_manage_video_chats;
        this.can_restrict_members = can_restrict_members;
        this.can_promote_members = can_promote_members;
        this.can_change_info = can_change_info;
        this.can_invite_users = can_invite_users;
        this.can_post_messages = can_post_messages;
        this.can_edit_messages = can_edit_messages;
        this.can_pin_messages = can_pin_messages;
        this.can_manage_topics = can_manage_topics;
    }

    public static ChatMemberAdministrator fromJson(String data) {
        return JsonSerializer.fromJson(data, ChatMemberAdministrator.class);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
