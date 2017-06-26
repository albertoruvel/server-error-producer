package com.messaging.error.producer.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.roar109.syring.annotation.ApplicationProperty;
import org.apache.log4j.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Qualifier;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Created by albertoruvel on 25/06/17.
 */
@ApplicationPath("rest/error")
public class ApplicationConfig extends Application{

    @Produces
    public Logger logger(InjectionPoint ip){
        return Logger.getLogger(ip.getMember().getDeclaringClass());
    }

    @Produces
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
