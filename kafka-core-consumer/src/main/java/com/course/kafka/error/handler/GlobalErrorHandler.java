package com.course.kafka.error.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GlobalErrorHandler implements CommonErrorHandler {

	@Override
	public void handleOtherException(Exception thrownException, Consumer<?, ?> consumer,
			MessageListenerContainer container, boolean batchListener) {
		log.warn("handleOtherException: Handling other exception : {}", thrownException.getMessage());
	}

	@Override
	public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer,
			MessageListenerContainer container) {
		log.warn("handleOne: Handling exception for record : {}, because : {}", record.value(), thrownException.getMessage());
		return false;
	}

}
