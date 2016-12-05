package core.objects;

public class TextMessage implements Message {

    public final Chat chat;
    public final String text;

    public TextMessage(Chat chat, String text) {
        this.chat = chat;
        this.text = text;
    }

    public Chat getChat() {
        return chat;
    }

    public MessageType getType() {
        return MessageType.TEXT_MESSAGE;
    }
}
