package com.course.kafka.broker.consumer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.broker.message.OrderMessage;

@Service
@Slf4j
public class OrderConsumer {

	@KafkaListener(topics = "t-commodity-order")
	public void listen(OrderMessage message) {
		// simulate processing
		var totalItemAmount = message.getPrice() * message.getQuantity();

		log.info("Processing order {}, item {}, credit card number {}. Total amount for this item is {}",
				message.getOrderNumber(), message.getItemName(), message.getCreditCardNumber(), totalItemAmount);
	}

}
