package com.grovs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DataAlreadyExists.class)
	public ResponseEntity<DataAlreadyExists> handlDataExistsException(DataAlreadyExists dataAlreadyExists) {
		return new ResponseEntity<DataAlreadyExists>(dataAlreadyExists, HttpStatus.CONFLICT);
	}
	@ExceptionHandler(DataMisMatchException.class)
	public ResponseEntity<DataMisMatchException> handleDataMisMatchExistsException(DataMisMatchException dataMisMatchException) {
		return new ResponseEntity<DataMisMatchException>(dataMisMatchException, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	@ExceptionHandler(AgeInsufficientException.class)
	public ResponseEntity<AgeInsufficientException> handleDataMisMatchExistsException(AgeInsufficientException ageInsufficientException) {
		return new ResponseEntity<AgeInsufficientException>(ageInsufficientException, HttpStatus.CONFLICT);
	}
}
