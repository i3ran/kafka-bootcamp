package com.course.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class RestartableProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    private AtomicInteger counter = new AtomicInteger();

    public void sendCounterMessage(){
        var msg = "Message " + counter.incrementAndGet();
        log.info("Sending message {}", msg);
        kafkaTemplate.send("t-restartable", msg);
    }
}
