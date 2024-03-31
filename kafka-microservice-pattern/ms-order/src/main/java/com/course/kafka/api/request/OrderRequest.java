package com.course.kafka.api.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {

	private String orderLocation;
	private String creditCardNumber;
	private List<OrderItemRequest> items;



}
