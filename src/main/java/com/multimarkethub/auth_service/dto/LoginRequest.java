package com.multimarkethub.auth_service.dto;

public class LoginRequest {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequest() {

    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

}
