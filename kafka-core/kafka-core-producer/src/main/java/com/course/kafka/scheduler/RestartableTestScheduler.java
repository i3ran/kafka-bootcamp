package com.course.kafka.scheduler;

import com.course.kafka.producer.RestartableProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RestartableTestScheduler {

    @Autowired
    private RestartableProducer producer;

    @Scheduled(fixedRate = 2000)
    public void runEvery2Sec(){
        producer.sendCounterMessage();
    }
}
