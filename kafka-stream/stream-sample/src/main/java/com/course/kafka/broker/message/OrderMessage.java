package com.course.kafka.broker.message;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderMessage {

	private String creditCardNumber;

	private String itemName;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime orderDateTime;

	private String orderLocation;

	private String orderNumber;

	private int price;

	private int quantity;


	
	public OrderMessage copy() {
		var copy = new OrderMessage();
		
		copy.setCreditCardNumber(this.getCreditCardNumber());
		copy.setItemName(this.getItemName());
		copy.setOrderDateTime(this.getOrderDateTime());
		copy.setOrderLocation(this.getOrderLocation());
		copy.setOrderNumber(this.getOrderNumber());
		copy.setPrice(this.getPrice());
		copy.setQuantity(this.getQuantity());
		
		return copy;
	}
}
