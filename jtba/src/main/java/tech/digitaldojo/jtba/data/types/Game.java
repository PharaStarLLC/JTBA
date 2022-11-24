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
import lombok.EqualsAndHashCode;
import tech.digitaldojo.jtba.data.Data;
import tech.digitaldojo.jtba.json.JsonSerializer;

import java.util.List;

/**
 * JTBA; tech.digitaldojo.jtba.data.types:Game
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @see <a href="https://core.telegram.org/bots/api#game">/bots/api#game</a>
 * @since 23.11.2022
 */
@EqualsAndHashCode
@AllArgsConstructor
public final class Game implements Data {

    /**
     * Title of the game
     */
    public final String title;

    /**
     * Description of the game
     */
    public final String description;

    /**
     * Photo that will be displayed in the game message in chats.
     */
    public final List<PhotoSize> photo;

    /**
     * Optional. Brief description of the game or high scores included in the game message. Can be automatically edited to include current high scores for the game when the bot calls setGameScore, or manually edited using editMessageText. 0-4096 characters.
     */
    public final String text;

    /**
     * Optional. Special entities that appear in text, such as usernames, URLs, bot commands, etc.
     */
    public final List<MessageEntity> text_entities;

    /**
     * Optional. Animation that will be displayed in the game message in chats. Upload via BotFather
     */
    public final Animation animation;

    /**
     * Creates a new {@link Game} instance of a given JSON {@link String}.
     *
     * @param data JSON {@link String}
     * @return {@link Game}
     */
    public static Game fromJson(String data) {
        return JsonSerializer.fromJson(data, Game.class);
    }

    /**
     * Convert a {@link Game} instance to a JSON {@link String}
     *
     * @return JSON {@link String}
     */
    @Override
    public String toString() {
        return this.toJson();
    }

}
