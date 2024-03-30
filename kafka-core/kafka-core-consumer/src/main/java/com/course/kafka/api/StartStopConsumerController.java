package com.course.kafka.api;

import com.course.kafka.consumer.RestartableConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/consumer/")
@Slf4j
public class StartStopConsumerController {

    @Autowired
    private RestartableConsumer consumer;

    @Autowired
    private KafkaListenerEndpointRegistry registry;

    @GetMapping("start/{name}")
    public void startConsumer(@PathVariable String name) {
        log.info("Starting consumer {}", name);

        registry.getListenerContainer(name).resume();
    }

    @GetMapping("stop/{name}")
    public void stopConsumer(@PathVariable String name) {
        log.info("Stopping consumer {}", name);

        registry.getListenerContainer(name).pause();
    }
}
