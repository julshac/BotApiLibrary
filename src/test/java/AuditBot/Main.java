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
        TelegramApi telegramApi = new TelegramApi("206274310:AAF2DmXLzBrNoTFtX2rj1cozJTtMReaBZ2U", bot, "delirium_bot2.0");

        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(telegramApi);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        bot.run();
    }
}
