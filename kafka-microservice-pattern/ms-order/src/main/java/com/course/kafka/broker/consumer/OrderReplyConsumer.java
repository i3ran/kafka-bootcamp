package com.course.kafka.broker.consumer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.broker.message.OrderReplyMessage;

@Service
@Slf4j
public class OrderReplyConsumer {


	@KafkaListener(topics = "t-commodity-order-reply")
	public void listen(OrderReplyMessage message) {
		log.info("Reply message received : {}", message);
	}
	
}
