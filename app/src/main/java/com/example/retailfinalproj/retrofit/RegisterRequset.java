package com.example.retailfinalproj.retrofit;

public class RegisterRequset {

    private String name;
    private String email;
    private String password;

    public RegisterRequset(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public RegisterRequset(String email, String password) {
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
