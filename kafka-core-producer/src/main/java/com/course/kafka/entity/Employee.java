package com.course.kafka.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonNaming(value = PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	private String employeeId;
	private String name;

// jackson.data-format works only when starter-web is added to the project. Otherwise use this annotation
//	@JsonFormat(pattern="yyyy-MMM-dd")
	private LocalDate birthDate;



}
