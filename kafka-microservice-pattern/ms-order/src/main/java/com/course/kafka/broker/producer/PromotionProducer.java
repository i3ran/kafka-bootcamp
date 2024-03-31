package com.course.kafka.broker.producer;

import java.util.concurrent.ExecutionException;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.broker.message.PromotionMessage;

@Service
@Slf4j
public class PromotionProducer {


	@Autowired
	private KafkaTemplate<String, PromotionMessage> kafkaTemplate;

	public void publish(PromotionMessage message) {
		try {
			var sendResult = kafkaTemplate.send("t-commodity-promotion", message).get();
			log.info("Send result success for message {}", sendResult.getProducerRecord().value());
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error publishing {}, because {}", message, e.getMessage());
		}
	}

}
