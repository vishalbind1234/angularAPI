package com.example.angularAPI.modal;

public class AuthClass {
    private String username;
    private String password;

    public AuthClass(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AuthClass() {
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
}
