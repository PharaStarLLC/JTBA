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
 * JTBA; tech.digitaldojo.jtba.data.types:ChatMemberOwner
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @see <a href="https://core.telegram.org/bots/api#chatmemberowner">/bots/api#chatmemberowner</a>
 * @since 23.11.2022
 */
@Getter
@Setter
public class ChatMemberOwner extends ChatMember {

    /**
     * Chat Member Type
     */
    public static ChatMemberType type = ChatMemberType.OWNER;

    /**
     * True, if the user's presence in the chat is hidden
     */
    public boolean is_anonymous;

    /**
     * Optional. Custom title for this user
     */
    public String custom_title;

    public ChatMemberOwner(final User user, final String status, final boolean is_anonymous, final String custom_title) {
        super(user, status);
        this.is_anonymous = is_anonymous;
        this.custom_title = custom_title;
    }

    public static ChatMemberOwner fromJson(String data) {
        return JsonSerializer.fromJson(data, ChatMemberOwner.class);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
