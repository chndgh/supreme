package com.bengi.model;

/**
 * Created by edward on 16/12/20.
 */
public class Message {

    private String username;
    private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "username='" + username + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
