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
 * JTBA; tech.digitaldojo.jtba.data.types:Chat
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @see <a href="https://core.telegram.org/bots/api#chat">/bots/api#chat</a>
 * @since 23.11.2022
 */
@AllArgsConstructor
@lombok.Data
public class Chat implements Data {

    public long id;
    public String type;
    public String title;
    public String username;
    public String first_name;
    public String last_name;
    public boolean is_forum;
    public ChatPhoto photo;
    public List<String> active_usernames;
    public String emoji_status_custom_emoji_id;
    public String bio;
    public boolean has_public_forwards;
    public boolean has_restricted_voice_and_video_messages;
    public boolean join_to_send_messages;
    public boolean join_by_request;
    public String description;
    public String invite_link;


    public static Chat fromJson(String data) {
        return JsonSerializer.fromJson(data, Chat.class);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
