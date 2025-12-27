package com.example.demo.dto;

public class AuthResponse {
    private int statusCodeValue;
    private String token;
    private Long userId;
    private String email;
    private String role;
    private String message;

    public AuthResponse() {}

    public AuthResponse(int statusCodeValue, String token, Long userId, String email, String role) {
        this.statusCodeValue = statusCodeValue;
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    public int getStatusCodeValue() {
        return statusCodeValue;
    }

    public void setStatusCodeValue(int statusCodeValue) {
        this.statusCodeValue = statusCodeValue;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
