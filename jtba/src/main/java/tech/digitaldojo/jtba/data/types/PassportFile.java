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
 * JTBA; tech.digitaldojo.jtba.data.types:PassportFile
 *
 * @author LuciferMorningstarDev - https://github.com/LuciferMorningstarDev
 * @see <a href="https://core.telegram.org/bots/api#passportfile">/bots/api#passportfile</a>
 * @since 23.11.2022
 */
@AllArgsConstructor
@lombok.Data
public class PassportFile implements Data {

    public String file_id;
    public String file_unique_id;
    public long file_size;
    public long file_date;

    public static PassportFile fromJson(String data) {
        return JsonSerializer.fromJson(data, PassportFile.class);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
