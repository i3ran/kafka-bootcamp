package com.course.kafka.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Commodity {
	private String name;

	private double price;

	private String measurement;
	private long timestamp;
	public void setPrice(double price) {
		this.price = Math.round(price * 100d) / 100d;
	}



}
