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

import lombok.val;
import tech.digitaldojo.jtba.data.types.Message;
import tech.digitaldojo.jtba.data.types.Update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * JTBA; tech.digitaldojo.jtba.events:EventEmitter
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 22.11.2022
 */
public class EventEmitter {

    private Map<MessageEvents, List<Consumer<Message>>> messageEvents;
    private Map<TelegramEvents, List<Consumer<Update>>> events;

    public EventEmitter() {
        messageEvents = new HashMap<>();
        events = new HashMap<>();
    }

    public boolean on(TelegramEvents event, Consumer<Update> consumer) {
        if (!events.containsKey(event)) {
            events.put(event, new ArrayList<>());
        }
        events.get(event).add(consumer);
        return true;
    }

    public boolean emit(TelegramEvents event, Update message) {
        if (!events.containsKey(event)) {
            return false;
        }
        val consumers = events.get(event);
        if (consumers.size() <= 0) {
            return false;
        }
        for (val consumer : consumers) {
            consumer.accept(message);
        }
        return true;
    }

    public boolean on(MessageEvents event, Consumer<Message> consumer) {
        if (!messageEvents.containsKey(event)) {
            messageEvents.put(event, new ArrayList<>());
        }
        messageEvents.get(event).add(consumer);
        return true;
    }

    public boolean emit(MessageEvents event, Message message) {
        if (!messageEvents.containsKey(event)) {
            return false;
        }
        val consumers = messageEvents.get(event);
        if (consumers.size() <= 0) {
            return false;
        }
        for (val consumer : consumers) {
            consumer.accept(message);
        }
        return true;
    }

}
