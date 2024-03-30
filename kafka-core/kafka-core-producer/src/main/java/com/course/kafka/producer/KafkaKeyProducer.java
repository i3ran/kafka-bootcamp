package com.course.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class KafkaKeyProducer {

//	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String key, String value) {
		log.info("Sending message with key {} and value {} to topic {}", key, value, "t-multi-partitions");
		kafkaTemplate.send("t-multi-partitions", key, value);
	}

}
