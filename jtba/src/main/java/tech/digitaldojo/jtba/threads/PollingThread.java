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

package tech.digitaldojo.jtba.threads;

import lombok.Getter;
import lombok.Setter;
import tech.digitaldojo.jtba.TelegramBot;
import tech.digitaldojo.jtba.data.types.Update;

import java.util.List;
import java.util.function.BiConsumer;

/**
 * JTBA; tech.digitaldojo.jtba.threads:PollingThread
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 22.11.2022
 */
public class PollingThread extends Thread {

    @Setter
    @Getter
    private BiConsumer<TelegramBot, List<Update>> whenDone;
    @Setter
    @Getter
    private BiConsumer<TelegramBot, List<Update>> afterAll;
    @Setter
    @Getter
    private BiConsumer<TelegramBot, Exception> onError;

    public PollingThread(Runnable runner) {
        super(runner, "PollingThread");
    }

    public PollingThread() {
        super("PollingThread");
    }

    public PollingThread then(BiConsumer<TelegramBot, List<Update>> whenDone) {
        this.setWhenDone(whenDone);
        return this;
    }

    public PollingThread catchError(BiConsumer<TelegramBot, Exception> onError) {
        this.setOnError(onError);
        return this;
    }

    public PollingThread all(BiConsumer<TelegramBot, List<Update>> afterAll) {
        this.setAfterAll(afterAll);
        return this;
    }

}
