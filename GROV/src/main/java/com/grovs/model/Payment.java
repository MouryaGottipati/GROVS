package com.grovs.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.grovs.enums.PaymentMode;
import com.grovs.enums.PaymentType;
import com.grovs.enums.Status;
import com.grovs.sequenceIdGenerator.SequenceIdGenerator;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_mode")
@Table(name="payment")
public class Payment {
	
	@Id
	@Column(name = "id",nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_sequence")
	@GenericGenerator(name = "payment_sequence", strategy = "com.grovs.sequenceIdGenerator.SequenceIdGenerator", parameters = {
			@Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "10"),
			@Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PMT_"),
			@Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%d") })
	private String id;
	
	@Column(name="transaction_id",nullable=false)
	private String transactionId;
	
	@Column(name="payment_mode",nullable=false)
	@Enumerated(EnumType.STRING)
	private PaymentMode mode;
	
	@Column(name="total_amount",nullable=false)
	private double totalAmount;
	
	@Column(name="status",nullable=false)
	@Enumerated(EnumType.STRING)
	private Status status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}



	public PaymentMode getMode() {
		return mode;
	}

	public void setMode(PaymentMode mode) {
		this.mode = mode;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
	

}
