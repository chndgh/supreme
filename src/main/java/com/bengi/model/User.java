package com.bengi.model;

import java.util.Date;

/**
 * Created by edward on 16/12/20.
 */
public class User {

    private Date time;
    private String username;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
