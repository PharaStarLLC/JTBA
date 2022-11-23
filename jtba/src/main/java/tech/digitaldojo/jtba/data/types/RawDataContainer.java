package tech.digitaldojo.jtba.data.types;

import lombok.AllArgsConstructor;
import tech.digitaldojo.jtba.data.Data;
import tech.digitaldojo.jtba.json.JsonSerializer;

/**
 * JTBA; tech.digitaldojo.jtba.data.types:RawDataContainer
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @see <a href="#">THIS IS NO API DATA WRAPPER</a>
 * @since 23.11.2022
 */
@AllArgsConstructor
@lombok.Data
public class RawDataContainer implements Data {

    /**
     * Creates a new {@link RawDataContainer} instance of a given JSON {@link String}.
     *
     * @param data JSON {@link String}
     * @return {@link RawDataContainer}
     */
    public static RawDataContainer fromJson(String data) {
        return JsonSerializer.fromJson(data, RawDataContainer.class);
    }

    /**
     * Convert a {@link RawDataContainer} instance to a JSON {@link String}
     *
     * @return JSON {@link String}
     */
    @Override
    public String toString() {
        return this.toJson();
    }

}
