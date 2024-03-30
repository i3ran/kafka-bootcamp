package com.course.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RestartableConsumer {

    @KafkaListener(id = "restartable.one",
            topics = "t-restartable",
            groupId = "cg-restartable.one",
            concurrency = "2")
    public void consumeOne(String message) {
        log.info("restartable.one: {}", message);
    }

    @KafkaListener(id = "restartable.two",
            topics = "t-restartable",
            groupId = "cg-restartable.two",
            concurrency = "2")
    public void consumeTwo(String message) {
        log.info("...............restartable.two: {}", message);
    }
}
