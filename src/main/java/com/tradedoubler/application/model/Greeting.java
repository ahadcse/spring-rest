package com.tradedoubler.application.model;

/**
 * Created by abdal on 2015-07-07.
 */
public class Greeting {
    private long id;
    private String content;
    private String greetingText;

    public Greeting(long id, String content, String greetingText) {
        this.id = id;
        this.content = content;
        this.greetingText = greetingText;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getText() {
        return greetingText;
    }
}
