package AuditBot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import telegram.TelegramApi;

public class Main {

    static {
        ApiContextInitializer.init();
    }


    public static void main(String[] args) {
        AuditBot bot = new AuditBot();
        TelegramApi telegramApi = new TelegramApi("319927751:AAFSavQaz3zhR7SOJWcs9ULCbOEIvbllWQA", bot, "BotApiLibrary");

        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(telegramApi);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        bot.run();
    }
}
