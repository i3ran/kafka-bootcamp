package com.course.kafka.broker.producer;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.course.kafka.broker.message.OrderMessage;

@Service
public class OrderProducer {

	private static final Logger LOG = LoggerFactory.getLogger(OrderProducer.class);

	@Autowired
	private KafkaTemplate<String, OrderMessage> kafkaTemplate;

	private ProducerRecord<String, OrderMessage> buildProducerRecord(OrderMessage message) {
		int surpriseBonus = StringUtils.startsWithIgnoreCase(message.getOrderLocation(), "A") ? 25 : 15;

		var headers = new ArrayList<Header>();
		var surpriseBonusHeader = new RecordHeader("surpriseBonus", Integer.toString(surpriseBonus).getBytes());
		headers.add(surpriseBonusHeader);

		return new ProducerRecord<String, OrderMessage>("t-commodity-order", null, message.getOrderNumber(), message,
				headers);
	}

	public void publish(OrderMessage message) {
		var producerRecord = buildProducerRecord(message);

		CompletableFuture<SendResult<String, OrderMessage>> future = kafkaTemplate.send(producerRecord);
		future.whenComplete((result, ex) -> {
			if (ex != null) {
				LOG.info("Order {}, item {} published successfuly", message.getOrderNumber(),
						message.getItemName());
			} else {
				LOG.error("Order {}, item {} failed to publish, because {}", message.getOrderNumber(),
						message.getItemName(), ex.getMessage());
				// do something else, maybe inserting to log database, etc
			}

		});
				

		LOG.info("Just a dummy messsage for order {}, item {} published successfuly", message.getOrderNumber(),
				message.getItemName());
	}

}
