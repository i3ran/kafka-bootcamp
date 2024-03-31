package com.course.kafka.broker.message;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DiscountMessage {

	private String discountCode;

	private int discountPercentage;

}
