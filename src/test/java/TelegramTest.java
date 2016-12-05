import core.Api;
import core.objects.Chat;
import core.objects.Message;
import core.objects.MessageType;
import core.objects.TextMessage;
import telegram.TelegramApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TelegramTest extends TelegramApi {
    List<String> greeting = Arrays.asList("Hi", "Hello", "Привет", "Здравствуй");

    @Override
    public String getToken() {
        return  "319927751:AAFSavQaz3zhR7SOJWcs9ULCbOEIvbllWQA";
    }

    @Override
    public void receiveMessage(Message message) {

       if (message.getType() == MessageType.TEXT_MESSAGE) {
           TextMessage textMessage = (TextMessage) message;
           if (greeting.contains(textMessage.text)) {
               sendMessage(new TextMessage(textMessage.chat, "Hello!"));
           } else {
               sendMessage(new TextMessage(textMessage.chat, "I don't understand you"));
           }
       }

    }

    @Override
    public String getBotUsername() {
        return "ApiLibraryBot";
    }
}
