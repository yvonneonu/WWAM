package com.example.waam;

public class otpResponse {
    private String message;

    public otpResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
