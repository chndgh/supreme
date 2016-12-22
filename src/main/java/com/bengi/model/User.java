package com.bengi.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


/**
 * Created by edward on 16/12/20.
 */
@Document
public class User {

    private String username;
    private String role;
    private String password;
    private Date time;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
