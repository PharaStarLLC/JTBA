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

package tech.digitaldojo.jtba.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * JTBA; tech.digitaldojo.jtba.json:JsonSerializer
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 22.11.2022
 */
@SuppressWarnings({"unchecked", "rawtypes"})
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonSerializer {

    private static PrettyJsonSerializer pretty = new PrettyJsonSerializer();

    @Getter
    private static Gson gson = new GsonBuilder().create();
    @Getter
    private static Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();


    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Class clazz) {
        return (T) gson.fromJson(json, clazz);
    }

    public static JsonElement fromJson(String json) {
        return JsonParser.parseString(json);
    }

    public static PrettyJsonSerializer pretty() {
        return pretty;
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class PrettyJsonSerializer {

        public String toJson(Object obj) {
            return gson.toJson(obj);
        }

        public <T> T fromJson(String json, Class clazz) {
            return (T) gson.fromJson(json, clazz);
        }

        public JsonElement fromJson(String json) {
            return JsonParser.parseString(json);
        }

    }

}
