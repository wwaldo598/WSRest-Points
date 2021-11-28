package com.app.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.enums.ErrorCodes;
import com.app.model.MessageCode;

@ControllerAdvice
public class TransactionExceptionHandler {

	Logger LOGGER = LoggerFactory.getLogger(TransactionExceptionHandler.class);
	
	@ExceptionHandler
	public ResponseEntity<MessageCode> handleException(TransactionNotFoundException ex){
		MessageCode errorResponse = new MessageCode();
		errorResponse.setStatus (HttpStatus.BAD_REQUEST.value());
		errorResponse.setCode   (ErrorCodes.ERR_TRANSACTION_NOT_EXIST.getCode());
		errorResponse.setMessage(ex.getMessage());
		LOGGER.error(ex.getMessage());
		return new ResponseEntity<MessageCode>(errorResponse, HttpStatus.BAD_REQUEST);
		
	}

	@ExceptionHandler
	public ResponseEntity<MessageCode> handleException(TransactionExistException ex){
		MessageCode errorResponse = new MessageCode();
		errorResponse.setStatus (HttpStatus.BAD_REQUEST.value());
		errorResponse.setCode   (ErrorCodes.ERR_TRANSACTION_ALREADY_EXIST.getCode());
		errorResponse.setMessage(ex.getMessage());
		LOGGER.error(ex.getMessage());
		return new ResponseEntity<MessageCode>(errorResponse, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler
	public ResponseEntity<MessageCode> handleException(Exception ex){
		MessageCode errorResponse = new MessageCode();
		errorResponse.setStatus (HttpStatus.BAD_REQUEST.value());
		errorResponse.setCode   (ErrorCodes.ERR_SERVER_INTERNAL.getCode());
		errorResponse.setMessage(ex.getMessage());
		return new ResponseEntity<MessageCode>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<MessageCode> handleException(HttpMessageNotReadableException ex){
		MessageCode errorResponse = new MessageCode();
		errorResponse.setStatus (HttpStatus.BAD_REQUEST.value());
		errorResponse.setCode   (ErrorCodes.ERR_PARAM_INVALID_VALUE.getCode());
		errorResponse.setMessage(ex.getMessage());
		LOGGER.error(ex.getMessage(), ex);
		return new ResponseEntity<MessageCode>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<MessageCode> handleException(ParameterInvalidException ex){
		MessageCode errorResponse = new MessageCode();
		errorResponse.setStatus (HttpStatus.BAD_REQUEST.value());
		errorResponse.setCode   (ex.getCode());
		errorResponse.setMessage(ex.getMessage());
		LOGGER.error(ex.getMessage());
		return new ResponseEntity<MessageCode>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	public ResponseEntity<MessageCode> handleException(CustomerNotFoundException ex){
		MessageCode errorResponse = new MessageCode();
		errorResponse.setStatus (HttpStatus.BAD_REQUEST.value());
		errorResponse.setCode   (ErrorCodes.ERR_CUSTOMER_NOT_EXIST.getCode());
		errorResponse.setMessage(ex.getMessage());
		LOGGER.error(ex.getMessage());
		return new ResponseEntity<MessageCode>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
