package tech.digitaldojo.jtba.data.types;

import lombok.AllArgsConstructor;
import tech.digitaldojo.jtba.data.Data;
import tech.digitaldojo.jtba.json.JsonSerializer;

/**
 * JTBA; tech.digitaldojo.jtba.data.types:ChatLocation
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @see <a href="https://core.telegram.org/bots/api#chatlocation">/bots/api#chatlocation</a>
 * @since 23.11.2022
 */
@AllArgsConstructor
@lombok.Data
public class ChatLocation implements Data {

    /**
     * The location to which the supergroup is connected. Can't be a live location.
     */
    public Location location;

    /**
     * Location address; 1-64 characters, as defined by the chat owner
     */
    public String address;

    /**
     * Creates a new {@link ChatLocation} instance of a given JSON {@link String}.
     *
     * @param data JSON {@link String}
     */
    public static ChatLocation fromJson(String data) {
        return JsonSerializer.fromJson(data, ChatLocation.class);
    }

    /**
     * Convert a {@link ChatLocation} instance to a JSON {@link String}
     *
     * @return JSON {@link String}
     */
    @Override
    public String toString() {
        return this.toJson();
    }

}
