package com.course.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.course.kafka.entity.Commodity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class CommodityNotificationConsumer {


//	@Autowired
	private ObjectMapper objectMapper;
	
	@KafkaListener(topics = "t-commodity", groupId = "cg-notification")
	public void consume(String message) throws JsonMappingException, JsonProcessingException {
		var commodity = objectMapper.readValue(message, Commodity.class);
		log.info("Notification logic for : {}", commodity);
	}
	
}
