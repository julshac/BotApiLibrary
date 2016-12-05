package core.objects;

import java.util.Optional;

public interface Message {
    Chat getChat();
    MessageType getType();

    default Optional<TextMessage> asTextMessage() {
        return getType() == MessageType.TEXT_MESSAGE ?
                Optional.of((TextMessage) this) : Optional.empty();
    }
}
