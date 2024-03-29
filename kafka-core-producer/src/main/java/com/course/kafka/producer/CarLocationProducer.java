package com.course.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.course.kafka.entity.CarLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Slf4j
public class CarLocationProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void send(CarLocation carLocation) throws JsonProcessingException {
		var json = objectMapper.writeValueAsString(carLocation);
		log.info("Sending car location : {}", carLocation);
		kafkaTemplate.send("t-location", json);
	}
	
}
