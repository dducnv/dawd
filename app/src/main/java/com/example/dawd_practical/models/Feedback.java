package com.example.dawd_practical.models;

public class Feedback {
    private long id;
    private String name;
    private String email;
    private String message;

    public Feedback() {
        super();
    }

    public Feedback(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }
    public Feedback(long id, String name, String email, String message) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
