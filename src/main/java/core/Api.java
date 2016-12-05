package core;

import core.objects.Message;

public interface Api {
    String getToken();
    void receiveMessage(Message message);
    void sendMessage(Message message);
}

/*

switch (message.getType()) {
    case MessageType.TEXT_MESSAGE:
        TextMessage m = message asInstanceOf TextMessage;
}
 */
