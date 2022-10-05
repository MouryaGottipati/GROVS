package com.grovs.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.grovs.enums.Offers;

import ch.qos.logback.core.status.Status;

@Entity
@Table(name="orders")
public class Order {

	private String id;
	
	private double total;
	
	private LocalDateTime placedTime;
	
	private LocalDateTime shipedTime;
	
	private LocalDateTime returnTime;
	
	@Enumerated(EnumType.STRING)
	private Offers offer;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToMany
	private OrderAddress billingAddress;
	
	@OneToMany
	private OrderAddress shippingAddress;
	
	@OneToOne
	private Payment payment;
	
	@OneToMany
	private Set<OrderItems> orderItems;
	
	
}
