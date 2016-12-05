package core;

import core.objects.Message;

public interface Api {
    String getToken();
    ApiHandler getHandler();
    default void receiveMessage(Message message) {
        getHandler().onReceiveMessage(message);
    }
    void sendMessage(Message message);
}

/*

switch (message.getType()) {
    case MessageType.TEXT_MESSAGE:
        TextMessage m = message asInstanceOf TextMessage;
}
 */
