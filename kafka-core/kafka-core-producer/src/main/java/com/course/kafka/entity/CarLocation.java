package com.course.kafka.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarLocation {

	private String carId;
	private long timestamp;
	private int distance;



}
