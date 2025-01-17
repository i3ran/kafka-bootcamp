package com.course.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.course.kafka.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InvoiceConsumer {


	@Autowired
	private ObjectMapper objectMapper;
	
	@KafkaListener(topics = "t-invoice", concurrency = "2", containerFactory = "invoiceDltContainerFactory")
	public void consume(String message) throws JsonMappingException, JsonProcessingException {
		var invoice = objectMapper.readValue(message, Invoice.class);
	
//		if (invoice.getAmount() < 100000) {
//			throw new IllegalArgumentException("Invalid amount for " + invoice);
//		}
		
		log.info("Processing invoice : {}", invoice);
	}
	
}
