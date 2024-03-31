package com.course.kafka.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue
	private int orderId;
	
	@Column
	private String orderNumber;
	
	@Column
	private String orderLocation;
	
	@Column
	private LocalDateTime orderDateTime;
	
	@Column
	private String creditCardNumber;
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> items;


}
