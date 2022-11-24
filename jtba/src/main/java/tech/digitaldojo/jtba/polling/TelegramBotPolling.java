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

package tech.digitaldojo.jtba.polling;

import com.google.gson.JsonObject;
import lombok.Getter;
import tech.digitaldojo.jtba.TelegramBot;
import tech.digitaldojo.jtba.data.types.Update;
import tech.digitaldojo.jtba.data.types.Updates;
import tech.digitaldojo.jtba.errors.FatalError;
import tech.digitaldojo.jtba.errors.ParseError;
import tech.digitaldojo.jtba.errors.TelegramError;
import tech.digitaldojo.jtba.events.TelegramEvents;

import java.util.Date;

/**
 * JTBA; tech.digitaldojo.jtba.polling:TelegramBotPolling
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @see <a href="https://core.telegram.org/bots/api#getting-updates">/bots/api#getting-updates</a>
 * @since 22.11.2022
 */
public class TelegramBotPolling {

    @Getter
    private final TelegramBot telegramBot;
    private int timeout = 0;
    private int limit = 100;
    private long offset = 0;
    private Thread pollingTimer;
    @Getter
    private long lastUpdate = 0L;
    private Updates lastRequest = null;

    public TelegramBotPolling(final TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void start() {
        if (pollingTimer != null) {
            if (pollingTimer.isAlive()) {
                pollingTimer.interrupt();
            }
        }
        pollingTimer = new Thread(() -> {
            while (true) {
                try {
                    polling();
                } catch (TelegramError e) {
                    error(e);
                    restart();
                } catch (ParseError e) {
                    error(e);
                    restart();
                } catch (FatalError e) {
                    error(e);
                    restart();
                } finally {
                    try {
                        Thread.sleep(this.telegramBot.getSettings().pollingIntervalMilliSeconds);
                    } catch (InterruptedException e) {
                        error(e);
                    }
                }
            }
        }, "TelegramBotPolling");
        pollingTimer.start();
    }

    private void restart() {
        this.stop();
        this.start();
    }

    public void stop() {
        if (pollingTimer != null) {
            this.pollingTimer.interrupt();
            pollingTimer = null;
        }
    }

    private void polling() throws TelegramError, ParseError, FatalError {
        JsonObject pollingForm = new JsonObject();
        pollingForm.addProperty("timeout", TelegramBotPolling.this.timeout);
        pollingForm.addProperty("limit", TelegramBotPolling.this.limit);
        pollingForm.addProperty("offset", TelegramBotPolling.this.offset);
        Updates updates = this.telegramBot.getUpdates(pollingForm);
        this.lastRequest = updates;
        this.lastUpdate = new Date().getTime();
        if (this.lastRequest == null || this.lastRequest.result == null) {
            throw new ParseError("Could not parse data.");
        }
        this.lastRequest.result.forEach(update -> {
            TelegramBotPolling.this.offset = update.update_id + 1;
            try {
                new Thread(() -> this.telegramBot.processUpdate(update), "update-" + TelegramBotPolling.this.offset).start();
            } catch (Exception e) {
                this.telegramBot.emit(TelegramEvents.error, Update.fromJson("{}"));
            }
        });
    }

    public boolean isPolling() {
        return (this.lastRequest != null);
    }

    private void error(Exception e) {
        this.telegramBot.emit(TelegramEvents.polling_error, Update.fromJson("{}"));
    }

}
