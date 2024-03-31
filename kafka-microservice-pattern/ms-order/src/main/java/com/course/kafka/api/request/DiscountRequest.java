package com.course.kafka.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountRequest {

	private String discountCode;

	private int discountPercentage;



}
