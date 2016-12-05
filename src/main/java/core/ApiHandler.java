package core;

import core.objects.Message;

public interface ApiHandler {
    void onReceiveMessage(Message message);
}
