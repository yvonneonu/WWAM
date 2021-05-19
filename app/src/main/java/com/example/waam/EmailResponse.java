package com.example.waam;

import java.io.Serializable;

public class EmailResponse implements Serializable {

    private String message;

    public EmailResponse( String message) {

        this.message = message;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
