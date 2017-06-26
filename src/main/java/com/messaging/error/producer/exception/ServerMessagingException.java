package com.messaging.error.producer.exception;

/**
 * Created by albertoruvel on 25/06/17.
 */
public class ServerMessagingException extends Exception {
    public ServerMessagingException(String message) {
        super(message);
    }

    public ServerMessagingException(String message, Throwable cause) {
        super(message, cause);
    }
}
