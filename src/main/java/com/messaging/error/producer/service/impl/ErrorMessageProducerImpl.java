package com.messaging.error.producer.service.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.github.roar109.syring.annotation.ApplicationProperty;
import com.google.gson.Gson;
import com.messaging.error.producer.data.ErrorMessageRequest;
import com.messaging.error.producer.data.ErrorMessageResponse;
import com.messaging.error.producer.exception.ServerMessagingException;
import com.messaging.error.producer.service.ErrorMessageProducer;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * Created by albertoruvel on 25/06/17.
 */
public class ErrorMessageProducerImpl implements ErrorMessageProducer {

    private AmazonSQS amazonSQS;

    @Inject
    @ApplicationProperty(name = "errors.queue.name", type = ApplicationProperty.Types.SYSTEM)
    private String errorQueueDestinationName;

    @Inject
    @ApplicationProperty(name="sqs.aws.access.key", type = ApplicationProperty.Types.SYSTEM)
    private String awsAccessKey;

    @Inject
    @ApplicationProperty(name="sqs.aws.secret.key", type = ApplicationProperty.Types.SYSTEM)
    private String awsSecretKey;

    @Inject
    private Logger log;

    @PostConstruct
    public void init(){
        amazonSQS = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSCredentialsProvider() {
                    public AWSCredentials getCredentials() {
                        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
                    }
                    public void refresh() {

                    }
                })
                .withRegion(Regions.US_WEST_2)
                .build();
    }

    public Response sendErrorMessage(ErrorMessageRequest request) throws ServerMessagingException{
        if(request.getApplicationId() == null || request.getApplicationId().isEmpty()){
            throw new ServerMessagingException("No application id was provided");
        }else if(request.getBody() == null || request.getBody().isEmpty()){
            throw new ServerMessagingException("No error body was provided");
        }
        final ErrorMessageResponse response = new ErrorMessageResponse();
        try{
            final String queueUrl = amazonSQS.getQueueUrl(errorQueueDestinationName).getQueueUrl();
            amazonSQS.sendMessage(new SendMessageRequest(queueUrl, new Gson().toJson(request)));
            response.setMessage("Message sent");
            response.setSuccess(true);
            return Response.ok(response).build();
        }catch(Exception ex){
            throw new ServerMessagingException(ex.getMessage());
        }

    }
}
