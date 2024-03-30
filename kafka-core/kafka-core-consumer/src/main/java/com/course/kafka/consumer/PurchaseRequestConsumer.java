package com.course.kafka.consumer;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;

import com.course.kafka.entity.PurchaseRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PurchaseRequestConsumer {


	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	@Qualifier("cachePurchaseRequest")
	private Cache<Integer, Boolean> cache;
	
	private boolean isExistsInCache(int purchaseRequestId) {
		return Optional.ofNullable(cache.getIfPresent(purchaseRequestId)).orElse(false);
	}
	
	@KafkaListener(topics = "t-purchase-request")
	public void consume(String message) throws JsonMappingException, JsonProcessingException {
		var purchaseRequest = objectMapper.readValue(message, PurchaseRequest.class);
		
		var processed = isExistsInCache(purchaseRequest.getId());
		
		if (processed) {
			log.info("{} purchase request is already procesed.", purchaseRequest);
			return;
		}
		
		log.info("Processing {}", purchaseRequest);
		
		cache.put(purchaseRequest.getId(), true);
	}
	
}
