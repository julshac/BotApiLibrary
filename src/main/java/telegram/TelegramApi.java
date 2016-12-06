package telegram;


import core.Api;
import core.ApiHandler;
import core.objects.Chat;
import core.objects.Message;
import core.objects.MessageType;
import core.objects.TextMessage;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class TelegramApi extends TelegramLongPollingBot implements Api {


    private final String token;
    private final ApiHandler handler;
    private final String name;

    public TelegramApi(String token, ApiHandler handler, String name) {
        this.token = token;
        this.handler = handler;
        this.name = name;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public ApiHandler getHandler() {
        return handler;
    }

    @Override
    public void sendMessage(Message message) {
        SendMessage libraryMessage = new SendMessage();
        libraryMessage.setChatId(message.getChat().id);
        if (message.getType() == MessageType.TEXT_MESSAGE) {
            libraryMessage.setText(((TextMessage)message).text);
        }
        try {
            sendMessage(libraryMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            org.telegram.telegrambots.api.objects.Message m = update.getMessage();
            if (m.hasText()) {
                String text = m.getText();
                long id = update.getMessage().getChatId();
                Chat chat = new Chat(id, this);
                Message message = new TextMessage(chat, text);
                receiveMessage(message);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }
}
