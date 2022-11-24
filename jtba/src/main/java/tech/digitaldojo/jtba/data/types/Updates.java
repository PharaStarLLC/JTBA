package tech.digitaldojo.jtba.data.types;

import lombok.AllArgsConstructor;
import tech.digitaldojo.jtba.data.Data;
import tech.digitaldojo.jtba.json.JsonSerializer;

import java.util.List;

/**
 * JTBA; tech.digitaldojo.jtba.data.types:Updates
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @see <a href="https://core.telegram.org/bots/api#getting-updates">/bots/api#getting-updates</a>
 * @since 24.11.2022
 */
@AllArgsConstructor
@lombok.Data
public class Updates implements Data {

    public boolean ok;

    public List<Update> result;

    public static Updates fromJson(String data) {
        return JsonSerializer.fromJson(data, Updates.class);
    }

    @Override
    public String toString() {
        return this.toJson();
    }

}
