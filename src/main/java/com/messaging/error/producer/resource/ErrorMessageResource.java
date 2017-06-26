package com.messaging.error.producer.resource;

import com.messaging.error.producer.data.ErrorMessageRequest;
import com.messaging.error.producer.exception.ServerMessagingException;
import com.messaging.error.producer.service.ErrorMessageProducer;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by albertoruvel on 25/06/17.
 */
@Path("/")
public class ErrorMessageResource {

    @Inject
    private ErrorMessageProducer errorMessageProducer;

    @POST
    @Path("send")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendErrorMessage(ErrorMessageRequest errorMessageRequest)throws ServerMessagingException{
        return errorMessageProducer.sendErrorMessage(errorMessageRequest);
    }
}
