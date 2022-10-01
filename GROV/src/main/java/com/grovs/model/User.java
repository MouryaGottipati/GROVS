package com.grovs.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.grovs.sequenceIdGenerator.SequenceIdGenerator;

@Entity
@Table(name = "users")
public class User {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@GenericGenerator(name = "user_seq", strategy = "com.grovs.sequenceIdGenerator.SequenceIdGenerator", parameters = {
			@Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "2"),
			@Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "U_"),
			@Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%08d") })
	private String id;
	@Column(name = "mail", nullable = false, unique = true)
	private String mail;
	@Column(name = "phone", nullable = false, unique = true)
	private String mobile;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "date_of_birth")
	private LocalDate date_of_birth;
	@Column(name = "age")
	private int age;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "middle_name")
	private String middle_name;
	@Column(name = "last_name", nullable = false)
	private String last_name;
	@Column(name = "creation_time", columnDefinition = "datetime default current_timestamp")
	private LocalDateTime creation_time;
	@Column(name = "role", nullable = false)
	private String role;
	/*
	 * @OneToMany(targetEntity = Cart.class,fetch=FetchType.EAGER)
	 * 
	 * @JoinColumn(name="cart_id",referencedColumnName ="id") private Set<Cart>
	 * cart;
	 */
	@OneToOne(targetEntity = Cart.class)
	@JoinColumn(name="cart_id",referencedColumnName="id")
	private Cart cartId;

	public String getId() {
		return id;
	}

	public String getMail() {
		return mail;
	}

	public String getMobile() {
		return mobile;
	}

	public String getPassword() {
		return password;
	}

	public LocalDate getDate_of_birth() {
		return date_of_birth;
	}

	public int getAge() {
		return age;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public LocalDateTime getCreation_time() {
		return creation_time;
	}

	public String getRole() {
		return role;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDate_of_birth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setCreation_time(LocalDateTime creation_time) {
		this.creation_time = creation_time;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
