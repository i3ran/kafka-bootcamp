package com.course.kafka.producer;

import com.course.kafka.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class Employee2JsonProducer {

//	@Autowired
	private ObjectMapper objectMapper;
	
//	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage(Employee employee) throws JsonProcessingException {
		var json = objectMapper.writeValueAsString(employee);
		log.info("Employee message: {}", json);
		kafkaTemplate.send("t-employee-2", json);
	}
	
}
