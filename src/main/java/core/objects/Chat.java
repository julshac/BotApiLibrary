package core.objects;


import core.Api;

public class Chat {
    public final String id;
    public final Api api;

    public Chat(String id, Api api) {
        this.id = id;
        this.api = api;
    }

    public Chat(long id, Api api) {
        this.id = String.valueOf(id);
        this.api = api;
    }

    public long longId() {
        return Long.valueOf(id);
    }
}
