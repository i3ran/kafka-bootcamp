package com.course.kafka.producer;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class RebalanceProducer {

//	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private AtomicInteger counter = new AtomicInteger();
	
	@Scheduled(fixedRate = 1000)
	public void sendMessage() {
		kafkaTemplate.send("t-rebalance", "Counter " + counter.incrementAndGet());
	}
	
}
