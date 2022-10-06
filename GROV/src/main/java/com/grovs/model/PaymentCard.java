package com.grovs.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.grovs.enums.PaymentMode;
import com.grovs.enums.PaymentType;

@DiscriminatorValue(value="CARD")
public class PaymentCard extends Payment{
		
	@Column(name="account_name",nullable=false)
	private String accountBankName;
	
	@Column(name="card_type",nullable=false)
	@Enumerated(EnumType.STRING)
	private PaymentType cardtype;
	
	@Column(name="account_number",nullable=false)
	private long cardNumber;
	
	@Column(name="account_holder_name",nullable=false)
	private String accountHolderName;
	
	@Column(name="expiry_date",nullable=false)
	private LocalDateTime expiryDate;
	
	@Column(name="cvv",nullable=false)
	private int cvv;

	public String getAccountBankName() {
		return accountBankName;
	}

	public void setAccountBankName(String accountBankName) {
		this.accountBankName = accountBankName;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	
	
	
}
