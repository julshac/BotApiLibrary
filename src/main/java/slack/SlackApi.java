package slack;


import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.methods.SlackApiException;
import com.github.seratch.jslack.api.methods.request.channels.ChannelsListRequest;
import com.github.seratch.jslack.api.methods.request.chat.ChatPostMessageRequest;
import com.github.seratch.jslack.api.methods.response.channels.ChannelsListResponse;
import com.github.seratch.jslack.api.methods.response.chat.ChatPostMessageResponse;
import com.github.seratch.jslack.api.model.Channel;
import core.Api;
import core.ApiHandler;
import core.objects.Message;
import core.objects.MessageType;
import core.objects.TextMessage;

public class SlackApi implements Api {

    private final String token;
    private final ApiHandler handler;

    public SlackApi(String token, ApiHandler handler) {
        this.token = token;
        this.handler = handler;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public ApiHandler getHandler() {
        return handler;
    }

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
