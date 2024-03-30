package com.course.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.course.kafka.entity.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ImageConsumer {

	@Autowired
	private ObjectMapper objectMapper;
	
	@KafkaListener(topics = "t-image", containerFactory = "imageRetryContainerFactory", concurrency = "2")
	public void consume(ConsumerRecord<String, String> consumerRecord) throws JsonMappingException, JsonProcessingException {
		var image = objectMapper.readValue(consumerRecord.value(), Image.class);
		
		if (image.getType().equalsIgnoreCase("svg")) {
			log.error("Throwing exception on partition {} for image {}", consumerRecord.partition(), image);
			throw new IllegalArgumentException("Simulate API call failed");
		}
		
		log.info("Processing on partition {} for image {}", consumerRecord.partition(), image);
	}
	
}
