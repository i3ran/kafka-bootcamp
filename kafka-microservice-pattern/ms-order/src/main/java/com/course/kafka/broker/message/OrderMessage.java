package com.course.kafka.broker.message;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class OrderMessage {

	private String orderLocation;
	private String orderNumber;
	private String creditCardNumber;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime orderDateTime;

	private String itemName;
	private int price;
	private int quantity;



}
