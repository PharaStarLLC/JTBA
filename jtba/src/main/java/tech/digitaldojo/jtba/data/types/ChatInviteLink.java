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

/**
 * JTBA; tech.digitaldojo.jtba.data.types:ChatInviteLink
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @see <a href="https://core.telegram.org/bots/api#chatinvitelink">/bots/api#chatinvitelink</a>
 * @since 23.11.2022
 */
@EqualsAndHashCode
@AllArgsConstructor
public final class ChatInviteLink implements Data {

    /**
     * The invite link. If the link was created by another chat administrator, then the second part of the link will be replaced with “…”.
     */
    public final String invite_link;

    /**
     * Creator of the link
     */
    public final User creator;

    /**
     * True, if users joining the chat via the link need to be approved by chat administrators
     */
    public final boolean creates_join_request;

    /**
     * True, if the link is primary
     */
    public final boolean is_primary;

    /**
     * True, if the link is revoked
     */
    public final boolean is_revoked;

    /**
     * Optional. Invite link name
     */
    public final String name;

    /**
     * Optional. Point in time (Unix timestamp) when the link will expire or has been expired
     */
    public final long expire_date;

    /**
     * Optional. The maximum number of users that can be members of the chat simultaneously after joining the chat via this invite link; 1-99999
     */
    public final long member_limit;

    /**
     * Optional. Number of pending join requests created using this link
     */
    public final long pending_join_request_count;

    /**
     * Creates a new {@link ChatInviteLink} instance of a given JSON {@link String}.
     *
     * @param data JSON {@link String}
     * @return {@link ChatInviteLink}
     */
    public static ChatInviteLink fromJson(String data) {
        return JsonSerializer.fromJson(data, ChatInviteLink.class);
    }

    /**
     * Convert a {@link ChatInviteLink} instance to a JSON {@link String}
     *
     * @return JSON {@link String}
     */
    @Override
    public String toString() {
        return this.toJson();
    }

}
