package com.course.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.course.kafka.entity.SimpleNumber;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Slf4j
public class SimpleNumberConsumer {


	@Autowired
	private ObjectMapper objectMapper;
	
	@KafkaListener(topics = "t-simple-number")
	public void consume(String message) throws JsonMappingException, JsonProcessingException {
		var simpleNumber = objectMapper.readValue(message, SimpleNumber.class);
		
		if (simpleNumber.getNumber() %2 != 0) {
			throw new IllegalArgumentException("Odd number : " + simpleNumber.getNumber());
		}
		
		log.info("Processing simpleNumber : {}", simpleNumber);
	}
	
}
