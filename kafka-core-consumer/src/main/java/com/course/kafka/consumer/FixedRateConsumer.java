package com.course.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class FixedRateConsumer {

    @KafkaListener(topics = "t-fixedrate")
    public void consume(String message) {
        log.info("Consuming: " + message);
    }
}
