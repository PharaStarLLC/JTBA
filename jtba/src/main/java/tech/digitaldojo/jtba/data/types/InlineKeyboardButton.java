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

/**
 * JTBA; tech.digitaldojo.jtba.data.types:InlineKeyboardButton
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @see <a href="https://core.telegram.org/bots/api#inlinekeyboardbutton">/bots/api#inlinekeyboardbutton</a>
 * @since 23.11.2022
 */
@AllArgsConstructor
@lombok.Data
public class InlineKeyboardButton implements Data {

    /**
     * Label text on the button
     */
    public String text;

    /**
     * Optional. HTTP or tg:// URL to be opened when the button is pressed. Links <code style="color: red; background-color: black;">tg://user?id={user_id}</code> can be used to mention a user by their ID without using a username, if this is allowed by their privacy settings.
     */
    public String url;

    /**
     * Optional. Data to be sent in a {@link CallbackQuery callback query} to the bot when button is pressed, 1-64 bytes
     */
    public String callback_data;

    /**
     * Optional. Description of the Web App that will be launched when the user presses the button. The Web App will be able to send an arbitrary message on behalf of the user using the method answerWebAppQuery. Available only in private chats between a user and the bot.
     */
    public WebAppInfo web_app;

    /**
     * Optional. An HTTPS URL used to automatically authorize the user. Can be used as a replacement for the Telegram Login Widget.
     */
    public LoginUrl login_url;

    /**
     * Optional. If set, pressing the button will prompt the user to select one of their chats, open that chat and insert the bot's username and the specified inline query in the input field. May be empty, in which case just the bot's username will be inserted.
     * <br />
     * Note: This offers an easy way for users to start using your bot in inline mode when they are currently in a private chat with it. Especially useful when combined with switch_pm… actions - in this case the user will be automatically
     * returned to the chat they switched from, skipping the chat selection screen.
     */
    public String switch_inline_query;

    /**
     * Optional. If set, pressing the button will insert the bot's username and the specified inline query in the current chat's input field. May be empty, in which case only the bot's username will be inserted.
     * <br />
     * This offers a quick way for the user to open your bot in inline mode in the same chat - good for selecting something from multiple options.
     */
    public String switch_inline_query_current_chat;

    /**
     * Optional. Description of the game that will be launched when the user presses the button.
     * <br />
     * NOTE: This type of button must always be the first button in the first row.
     */
    public CallbackGame callback_game;

    /**
     * Optional. Specify True, to send a Pay button.
     * <br />
     * NOTE: This type of button must always be the first button in the first row and can only be used in invoice messages.
     */
    public boolean pay;

    /**
     * Creates a new {@link InlineKeyboardButton} instance of a given JSON {@link String}.
     *
     * @param data JSON {@link String}
     * @return {@link InlineKeyboardButton}
     */
    public static InlineKeyboardButton fromJson(String data) {
        return JsonSerializer.fromJson(data, InlineKeyboardButton.class);
    }

    /**
     * Convert a {@link InlineKeyboardButton} instance to a JSON {@link String}
     *
     * @return JSON {@link String}
     */
    @Override
    public String toString() {
        return this.toJson();
    }

}
