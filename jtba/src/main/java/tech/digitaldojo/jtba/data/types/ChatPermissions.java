package tech.digitaldojo.jtba.data.types;

import lombok.AllArgsConstructor;
import tech.digitaldojo.jtba.data.Data;
import tech.digitaldojo.jtba.json.JsonSerializer;

/**
 * JTBA; tech.digitaldojo.jtba.data.types:ChatPermissions
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @see <a href="https://core.telegram.org/bots/api#chatpermissions">/bots/api#chatpermissions</a>
 * @since 23.11.2022
 */
@AllArgsConstructor
@lombok.Data
public class ChatPermissions implements Data {

    /**
     * Optional. True, if the user is allowed to send text messages, contacts, locations and venues
     */
    public boolean can_send_messages;

    /**
     * Optional. True, if the user is allowed to send audios, documents, photos, videos, video notes and voice notes, implies can_send_messages
     */
    public boolean can_send_media_messages;

    /**
     * Optional. True, if the user is allowed to send polls, implies can_send_messages
     */
    public boolean can_send_polls;

    /**
     * Optional. True, if the user is allowed to send animations, games, stickers and use inline bots, implies can_send_media_messages
     */
    public boolean can_send_other_messages;

    /**
     * Optional. True, if the user is allowed to add web page previews to their messages, implies can_send_media_messages
     */
    public boolean can_add_web_page_previews;

    /**
     * Optional. True, if the user is allowed to change the chat title, photo and other settings. Ignored in public supergroups
     */
    public boolean can_change_info;

    /**
     * Optional. True, if the user is allowed to invite new users to the chat
     */
    public boolean can_invite_users;

    /**
     * Optional. True, if the user is allowed to pin messages. Ignored in public supergroups
     */
    public boolean can_pin_messages;

    /**
     * Optional. True, if the user is allowed to create forum topics. If omitted defaults to the value of can_pin_messages
     */
    public boolean can_manage_topics;

    /**
     * Creates a new {@link ChatPermissions} instance of a given JSON {@link String}.
     *
     * @param data JSON {@link String}
     * @return {@link ChatPermissions}
     */
    public static ChatPermissions fromJson(String data) {
        return JsonSerializer.fromJson(data, ChatPermissions.class);
    }

    /**
     * Convert a {@link ChatPermissions} instance to a JSON {@link String}
     *
     * @return JSON {@link String}
     */
    @Override
    public String toString() {
        return this.toJson();
    }

}
