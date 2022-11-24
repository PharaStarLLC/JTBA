# Instantiation

#### Instantiate Default TelegramBot

```java
import tech.digitaldojo.jtba.TelegramBot;

public class MyProject {

    private static TelegramBot bot;

    public static void main(String[] args) {
        if (args.length < 1) throw new IllegalArgumentException("Token cannot be null please insert it as first program argument.");
        String token = args[0];
        // creates a bot with polling active ( fetching updates all 300ms ) 
        bot = new TelegramBot(token);
    }

}
```

---

# Add EventHandlers

#### Adding default Error Handler

The error event runs if there is an error on making requests in polling or webhooks

```java
import tech.digitaldojo.jtba.TelegramBot;
import tech.digitaldojo.jtba.events.TelegramEvents;

class Showcase {

    private TelegramBot bot;

    private void anything() {
        bot.on(TelegramEvents.error, System.out::println);
    }

}
```

---

#### Adding Default ReadyHandler

This event runs if the bot is able to make API requests.

```java
import tech.digitaldojo.jtba.TelegramBot;
import tech.digitaldojo.jtba.events.TelegramEvents;

class Showcase {

    private TelegramBot bot;

    private void anything() {
        bot.on(TelegramEvents.ready, (user) -> {
            System.out.println("Bot Ready: " + user);
        });
    }

}
```

---

#### Adding Default TextMessageHandler

The text event runs if the message has any text set to the message object

```java
import tech.digitaldojo.jtba.TelegramBot;
import tech.digitaldojo.jtba.events.MessageEvents;

class Showcase {

    private TelegramBot bot;

    private void anything() {
        bot.on(MessageEvents.text, (message) -> {
            System.out.println("Received Text: " + user);

            String txt = message.text;
            if (txt.startsWith("-echo ")) {
                txt = txt.replaceFirst("-echo ", "");
                message.chat.send(txt);
                message.replyMarkdown("```txt\n" + txt + "\n```");
            }
        });
    }

}
```

---

#### Handlers with "external" Consumer

```java
import tech.digitaldojo.jtba.TelegramBot;
import tech.digitaldojo.jtba.data.types.Message;
import tech.digitaldojo.jtba.events.MessageEvents;
import tech.digitaldojo.jtba.events.TelegramEvents;

import java.util.function.Consumer;

class Showcase {

    private TelegramBot bot;

    private void anything() {
        bot.on(TelegramEvents.message, Showcase::onMessage);
        bot.on(MessageEvents.text, new MyMessageConsumer(bot));
    }

    public void onMessage(Message message) {
        // whatever
    }

    class MyMessageConsumer extends Consumer<Message> {

        private final TelegramBot bot;

        public MyMessageConsumer(TelegramBot bot) {
            this.bot = bot;
        }

        @Override
        public void accept(final Message message) {
            System.out.println("Message: " + message);
        }

    }

}
```
