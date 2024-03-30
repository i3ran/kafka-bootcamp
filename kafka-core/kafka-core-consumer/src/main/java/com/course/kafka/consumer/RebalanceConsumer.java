package com.course.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class RebalanceConsumer {


	@KafkaListener(topics = "t-rebalance", concurrency = "3")
	public void consume(ConsumerRecord<String, String> consumerRecord) {
		log.info("Partition : {}, Offset : {}, Message : {}", consumerRecord.partition(), consumerRecord.offset(),
				consumerRecord.value());
	}

}
