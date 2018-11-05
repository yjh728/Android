package com.example.yjh.chartroomtest;

public class Msg {
    public static final int TYPE_SEND = 1;
    public static final int TYPE_RECEIVE = 0;
    private int type;
    private String content;

    public Msg(int type, String content) {
        this.type = type;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }
}
