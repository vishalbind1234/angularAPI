package com.example.angularAPI.modal;

public class UserResponse {
    private int id;
    private String name;
    private String phome;
    private String email;
    public UserResponse(int id, String name, String phome, String email) {
        super();
        this.id = id;
        this.name = name;
        this.phome = phome;
        this.email = email;
    }
    public UserResponse() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhome() {
        return phome;
    }

    public void setPhome(String phome) {
        this.phome = phome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
