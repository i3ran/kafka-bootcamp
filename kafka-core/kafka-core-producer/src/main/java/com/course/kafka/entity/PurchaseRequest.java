package com.course.kafka.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseRequest {

	private int id;
	private String prNumber;
	private int amount;
	private String currency;



}
