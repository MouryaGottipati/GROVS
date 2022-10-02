package com.grovs.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.grovs.enums.UserRole;
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
	@Email
	@NotEmpty
	@Column(name = "mail", nullable = false, unique = true)
	private String mail;
	@NotEmpty
	@Size(min = 10, max = 11, message = "Number of digits in phone number must be ten")
	@Column(name = "phone", nullable = false, unique = true)
	private String mobile;
	@NotEmpty
	@Size(min = 8, message = "Password length must be atleast 8 characters")
	@Column(name = "password", nullable = false)
	private String password;
	@NotEmpty
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	@Column(name = "age")
	private int age;
	@NotEmpty
	@Size(min = 2, message = "minimum 2 characters required")
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "middle_name")
	private String middleName;
	@NotEmpty
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "creation_time", columnDefinition = "datetime default current_timestamp")
	private LocalDateTime creationTime;
	@Column(name = "role", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRole role;
	/*
	 * @OneToMany(targetEntity = Cart.class,fetch=FetchType.EAGER)
	 * 
	 * @JoinColumn(name="cart_id",referencedColumnName ="id") private Set<Cart>
	 * cart;
	 */
	@OneToOne(targetEntity = Cart.class)
	@JoinColumn(name = "cart_id", referencedColumnName = "id")
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

	public int getAge() {
		return age;
	}

	public String getFirstName() {
		return firstName;
	}



	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
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

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public Cart getCartId() {
		return cartId;
	}

	public void setCartId(Cart cartId) {
		this.cartId = cartId;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	

}
