package com.grovs.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.grovs.sequenceIdGenerator.SequenceIdGenerator;

@Entity
@Table(name="address")
public class Address {
	
	@Id
	@Column(name = "id",nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
	@GenericGenerator(name = "address_seq", strategy = "com.grovs.sequenceIdGenerator.SequenceIdGenerator", parameters = {
			@Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "A_"),
			@Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%01d") })
	private String id;
	
	@ManyToOne(targetEntity=User.class,cascade=CascadeType.ALL)
	@JoinColumn(name="users_id",referencedColumnName="id",nullable=false)
	private String userId;
	
	@NotEmpty(message="first name can't be empty")
	@Size(min=2,message="Minimum length of first name must be 2")
	@Column(name="first_name",nullable=false)	
	private String firstName;
	/*
	 * @NotEmpty(message="last name can't be empty")
	 * 
	 * @Column(name="last_name",nullable=false) private String lastName;
	 */
	@NotEmpty(message="phone number can't be empty")
	@Size(min=10,max=10,message="Phone number length must be 10")
	@Column(name="phone",nullable=false)
	private String phone;
	@NotEmpty(message="House number can't be empty")
	@Column(name="houseNo",nullable=false)
	private String  houseNo;
	@NotEmpty(message="street can't be empty")
	@Size(min=2,message="Minimum length of street must be 2")
	@Column(name="street",nullable=false)
	private String street;
	@NotEmpty(message="City can't be empty")
	@Size(min=2,message="Minimum length of city name must be 2")
	@Column(name="city",nullable=false)
	private String city;
	/* @NotEmpty(message="Pincode can't be empty") */
	@Min(message="Pincode length  must be 6 digits", value = 100000)
	@Max(message="Pincode length  must be 6 digits", value = 999999)
	@Column(name="pincode",nullable=false)
	private int pincode;
	
	@Column(name="type")
	private String type;
	
	@Column(name="updated_time",nullable=false,columnDefinition = "datetime default current_timestamp on update current_timestamp")
	private LocalDateTime updatedTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/*
	 * public String getLastName() { return lastName; }
	 * 
	 * public void setLastName(String lastName) { this.lastName = lastName; }
	 */

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

	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}

}
