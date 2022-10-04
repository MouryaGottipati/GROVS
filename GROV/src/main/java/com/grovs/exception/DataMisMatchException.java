package com.grovs.exception;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class DataMisMatchException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = 3186573010276228704L;
	
	private String errorCode;
	private String errorMessage;
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public DataMisMatchException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public DataMisMatchException() {
		super();
	}
	
	
	
	
}
