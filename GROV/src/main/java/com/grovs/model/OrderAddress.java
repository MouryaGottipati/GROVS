package com.grovs.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.grovs.sequenceIdGenerator.SequenceIdGenerator;

@Entity
@Table(name="order_address")
public class OrderAddress {
	
	@Id
	@Column(name = "id",nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_address_seq")
	@GenericGenerator(name = "order_address_seq", strategy = "com.grovs.sequenceIdGenerator.SequenceIdGenerator", parameters = {
			@Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "OA_"),
			@Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%d") })
	private String id;
	

	
	@Column(name="name",nullable=false)	
	private String firstName;
	
	@Column(name="mail",nullable=false)
	private String mail;
	
	@Column(name="phone",nullable=false)
	private String phone;
	
	@Column(name="houseNo",nullable=false)
	private String  houseNo;
	
	@Column(name="street",nullable=false)
	private String street;
	
	@Column(name="city",nullable=false)
	private String city;
	
	@Column(name="pincode",nullable=false)
	private int pincode;
	
	@Column(name="type")
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
