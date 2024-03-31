package com.course.kafka.broker.consumer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.broker.message.DiscountMessage;
import com.course.kafka.broker.message.PromotionMessage;

@Service
@Slf4j
@KafkaListener(topics = "t-commodity-promotion")
public class PromotionConsumer {


	@KafkaHandler
	public void listenPromotion(PromotionMessage message) {
		log.info("Processing promotion : {}", message);
	}

	@KafkaHandler
	public void listenDiscount(DiscountMessage message) {
		log.info("Processing discount : {}", message);
	}

}
