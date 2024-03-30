package com.course.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.course.kafka.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

//@Service
@Slf4j
public class EmployeeJsonConsumer {


	private final ObjectMapper objectMapper;

	public EmployeeJsonConsumer(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@KafkaListener(topics = "t-employee-2")
	public void listen(String message) throws JsonProcessingException {
		var employee = objectMapper.readValue(message, Employee.class);
		log.info("Employee is : {}", employee);
	}
	
}
