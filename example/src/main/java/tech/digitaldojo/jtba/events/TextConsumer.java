package tech.digitaldojo.jtba.events;

import tech.digitaldojo.jtba.TelegramBot;
import tech.digitaldojo.jtba.data.types.Message;

import java.util.function.Consumer;

/**
 * JTBA; tech.digitaldojo.jtba.events:TextConsumer
 *
 * @author <a href="https://github.com/LuciferMorningstarDev">LuciferMorningstarDev</a>
 * @since 24.11.2022
 */
public class TextConsumer implements Consumer<Message> {

    private final TelegramBot bot;

    public TextConsumer(TelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void accept(final Message message) {

        System.out.println("MessageEvents.text: " + message);
        String txt = message.text;
        if (txt.startsWith("-echo ")) {
            txt = txt.replaceFirst("-echo ", "");
            message.chat.send(txt);
            message.replyMarkdown("```txt\n" + txt + "\n```");
        }

    }

}
