package com.messaging.error.producer.data;

/**
 * Created by albertoruvel on 25/06/17.
 */
public class ErrorMessageResponse {
    private String message;
    private boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
