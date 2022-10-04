package com.grovs.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class LoginUserModel {
	
	@Email(message="Mail format incorrect")
	@NotEmpty(message="Mail can't be empty")
	private String mail;
	
	@NotEmpty(message="Password can't be empty")
	@Size(min = 8, message = "Password length must be atleast 8 characters")
	private String password;

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
