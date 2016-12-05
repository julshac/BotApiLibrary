package slack;


import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.channels.ChannelsListRequest;
import com.github.seratch.jslack.api.methods.request.chat.ChatPostMessageRequest;
import com.github.seratch.jslack.api.methods.response.channels.ChannelsListResponse;
import com.github.seratch.jslack.api.methods.response.chat.ChatPostMessageResponse;
import com.github.seratch.jslack.api.model.Channel;
import core.Api;
import core.objects.Message;
import core.objects.MessageType;
import core.objects.TextMessage;

public abstract class SlackApi implements Api {

    private Slack slack = Slack.getInstance();

    @Override
    public void sendMessage(Message message) {
        if (message.getType() == MessageType.TEXT_MESSAGE) {
            sendTextMessage((TextMessage) message);
        }
    }

    private void sendTextMessage(TextMessage message) {
        try {
            slack.methods().chatPostMessage(
                    ChatPostMessageRequest
                            .builder()
                            .token(getToken())
                            .channel(message.chat.id)
                            .text(message.text)
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
