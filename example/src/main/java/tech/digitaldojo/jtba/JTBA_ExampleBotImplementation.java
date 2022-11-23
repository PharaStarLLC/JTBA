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

package tech.digitaldojo.jtba;

import tech.digitaldojo.jtba.events.MessageEvents;
import tech.digitaldojo.jtba.events.TelegramEvents;
import tech.digitaldojo.jtba.json.JsonSerializer;

/**
 * JTBA; tech.digitaldojo.jtba:JTBA_ExampleBotImplementation
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @since 23.11.2022
 */
public class JTBA_ExampleBotImplementation {

    private static TelegramBot bot;

    public static void main(String[] args) {
        System.out.println("Starting...");
        String token = "5625977159:AAH-GPkavkWrAponVZwXYViS_U3wL0BgJWI";
        bot = new TelegramBot(token);

        System.out.println(JsonSerializer.pretty());

        // Add any error handler
        bot.on(TelegramEvents.error, System.out::println);

        // when the bot setup is done this ready event gets fired
        bot.on(TelegramEvents.ready, (user) -> {
            System.out.println("BOT READY!! TelegramEvents.ready: " + user);
        });
        // listen for events
        bot.on(TelegramEvents.chat_join_request, (message) -> {
            System.out.println("TelegramEvents.chat_join_request: " + message);
        });
        bot.on(TelegramEvents.callback_query, (message) -> {
            System.out.println("TelegramEvents.callback_query: " + message);
        });
        // listen for all kinds of messages
        bot.on(TelegramEvents.message, (message) -> {
            System.out.println("TelegramEvents.message: " + message);
        });
        // listen for specific kinds of messages
        bot.on(MessageEvents.text, (message) -> {
            System.out.println("MessageEvents.text: " + message);
            String txt = message.text;
            if (txt.startsWith("-echo ")) {
                txt = txt.replaceFirst("-echo ", "");
                bot.sendMessage(message.chat.id, txt);
            }
        });
        bot.on(MessageEvents.new_chat_title, (message) -> {
            System.out.println("MessageEvents.new_chat_title: " + message);
        });
        bot.on(MessageEvents.new_chat_members, (message) -> {
            System.out.println("MessageEvents.new_chat_members: " + message);
        });
        bot.on(MessageEvents.poll, (message) -> {
            System.out.println("MessageEvents.poll: " + message);
        });


    }

}
