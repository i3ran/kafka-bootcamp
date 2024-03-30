package com.course.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class HelloKafkaConsumer {

    @KafkaListener(topics = "t-hello")
    public void consume(String msg){
        System.out.println("----------------Message received: " + msg);
    }
}
