package com.course.kafka.broker.consumer;

import com.course.avro.data.Avro01;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Avro01Consumer {

    @KafkaListener(topics = "sc-avro01")
    public void listen(ConsumerRecord<String, Avro01> message) {
        log.info("Received message with key: {}, and value : {}", message.key(), message.value());
    }

}
