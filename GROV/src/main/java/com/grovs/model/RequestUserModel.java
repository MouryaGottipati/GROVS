package com.grovs.model;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;



@Component
//@JsonDeserialize
public class RequestUserModel {
	
	@Email(message="Mail format incorrect")
	@NotEmpty(message="Mail can't be empty")
	private String mail;
	@NotEmpty(message="mobile can't empty")
	@Size(min = 10, max = 10, message = "Number of digits in phone number must be ten")
	private String mobile;
	@NotEmpty(message="Password can't be empty")
	@Size(min = 8, message = "Password length must be atleast 8 characters")
	private String password;
	@NotEmpty(message="dateOfBirth can't be empty")
	private String dateOfBirth;
	@NotEmpty(message="firstName can't be empty")
	@Size(min = 2, message = "minimum 2 characters required")
	private String firstName;
	private String middleName;
	@NotEmpty(message="lastName can't be empty")
	private String lastName;
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	@Override
	public String toString() {
		return "RequestUserModel [mail=" + mail + ", mobile=" + mobile + ", password=" + password + ", dateOfBirth="
				+ dateOfBirth + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ "]";
	}
	

	
	
}
