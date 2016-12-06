package AuditBot;

import core.ApiHandler;
import core.objects.Chat;
import core.objects.Message;
import core.objects.TextMessage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
    This code is kind of shit(but it works). Please fix it someone!
 */

public class AuditBot implements ApiHandler {

    private Path logPath = Paths.get("/usr/local/var/postgres/server.log");
    private InputStream input = getFileInputStream(logPath, null);

    private List<Chat> chats = new ArrayList<>();

    @Override
    public void onReceiveMessage(Message message) {
        if (message.asTextMessage().filter(m -> Objects.equals(m.text, "/start")).isPresent()) {
            chats.add(message.getChat());
            return;
        }
        Path path = message.asTextMessage().map(m -> m.text).filter(m -> m.toLowerCase().startsWith("/setlog"))
                .map(m -> Paths.get(m.split(" ")[1])).orElse(logPath);
        synchronized (this) {
            logPath = path;
            InputStream stream = getFileInputStream(path, input);
            if (stream == input) {
                message.getChat().api.sendMessage(new TextMessage(message.getChat(), "Log path is invalid"));
            } else {
                input = stream;
            }
        }
    }

    void run() {
        System.out.println("Ready");
        while (true) {
            try {
                StringBuilder sb = new StringBuilder();
                while (input.available() != 0) {
                    sb.append((char)input.read());
                }
                if (sb.length() != 0) {
                    chats.forEach(chat -> chat.api.sendMessage(new TextMessage(chat, sb.toString())));
                }
                Thread.sleep(10000); // we should not check log file too often
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static InputStream getFileInputStream(Path path, InputStream oldStream) {
        try {
            InputStream stream = new BufferedInputStream(Files.newInputStream(path));
            stream.skip(stream.available());
            if (oldStream != null) oldStream.close();
            return stream;
        } catch (IOException e) {
            e.printStackTrace();
            return oldStream;
        }
    }
}
