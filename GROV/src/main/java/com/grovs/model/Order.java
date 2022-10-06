package com.grovs.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.grovs.enums.Offers;
import com.grovs.enums.Status;
import com.grovs.sequenceIdGenerator.SequenceIdGenerator;


@Entity
@Table(name="orders")
public class Order {
	
	@Id
	@Column(name = "id",nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
	@GenericGenerator(name = "order_sequence", strategy = "com.grovs.sequenceIdGenerator.SequenceIdGenerator", parameters = {
			@Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "OS_"),
			@Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%d")})
	private String id;
	
	@Column(name="total_amount",nullable=false)
	private double totalAmount;
	
	@Column(name="placed_time",nullable=false,columnDefinition = "DateTime default current_timestamp")
	private LocalDateTime placedTime;
	
	@Column(name="shipped_time",nullable=false)
	private LocalDateTime shippedTime;
	
	@Column(name="return_time",nullable=false)
	private LocalDateTime returnTime;
	
	@Column(name="offer_applied")
	@Enumerated(EnumType.STRING)
	private Offers offerApplied;
	
	@Column(name="status",nullable=false)
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="billing_address_id",referencedColumnName="id")
	private OrderAddress billingAddress;
	
	@ManyToOne
	@JoinColumn(name="shipping_address_id",referencedColumnName="id")
	private OrderAddress shippingAddress;
	
	
	@OneToOne
	@JoinColumn(name="payment_id",referencedColumnName="id")
	private Payment payment;
	
	@OneToMany(mappedBy="orderId")
	private Set<OrderItems> orderItems;

	@Column(name="user_id",nullable=false)
	private String userId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDateTime getPlacedTime() {
		return placedTime;
	}

	public void setPlacedTime(LocalDateTime placedTime) {
		this.placedTime = placedTime;
	}

	public LocalDateTime getShippedTime() {
		return shippedTime;
	}

	public void setShippedTime(LocalDateTime shippedTime) {
		this.shippedTime = shippedTime;
	}

	public LocalDateTime getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(LocalDateTime returnTime) {
		this.returnTime = returnTime;
	}

	public Offers getOfferApplied() {
		return offerApplied;
	}

	public void setOfferApplied(Offers offerApplied) {
		this.offerApplied = offerApplied;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public OrderAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(OrderAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	public OrderAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(OrderAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Set<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}
	
	
}
