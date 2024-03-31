package com.course.kafka.broker.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.broker.message.DiscountMessage;

@Service
public class DiscountProducer {

	@Autowired
	private KafkaTemplate<String, DiscountMessage> kafkaTemplate;

	// This DiscountProducer is publishing messages on same topic (t-commodity-promotion)
	// but different message types (PromotionMessage and DiscountMessage)
	// The ms-storage project has a consumer from this topic processing both types of messages.
	public void publish(DiscountMessage message) {
		kafkaTemplate.send("t-commodity-promotion", message);
	}

}
