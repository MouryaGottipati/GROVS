package com.grovs.exception;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
public class DataAlreadyExists extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3025860190279613455L;
	public DataAlreadyExists() {
		super();
	}
	public DataAlreadyExists(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		
	}
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
	private String errorCode;
	private String errorMessage;

		
}
