package com.messaging.error.producer.exception.mapper;

import com.messaging.error.producer.data.ErrorMessageResponse;
import com.messaging.error.producer.exception.ServerMessagingException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Created by albertoruvel on 25/06/17.
 */
@Provider
public class ServerMessagingExceptionMapper implements ExceptionMapper<ServerMessagingException> {
    public Response toResponse(ServerMessagingException e) {
        ErrorMessageResponse response = new ErrorMessageResponse();
        response.setMessage("Internal Server Error: " + e.getMessage());
        response.setSuccess(false);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(response)
                .build();
    }
}
