package com.messaging.error.producer.service;

import com.messaging.error.producer.data.ErrorMessageRequest;
import com.messaging.error.producer.exception.ServerMessagingException;

import javax.ws.rs.core.Response;

/**
 * Created by albertoruvel on 25/06/17.
 */
public interface ErrorMessageProducer {
    public Response sendErrorMessage(ErrorMessageRequest request) throws ServerMessagingException;
}