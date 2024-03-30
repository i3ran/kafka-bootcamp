package com.course.kafka.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDate;

//@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonNaming(value = PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

	private String employeeId;
	private String name;

	@JsonFormat(pattern="yyyy-MMM-dd") // jackson.data-format is NOT working. hence using this
	private LocalDate birthDate;
}
