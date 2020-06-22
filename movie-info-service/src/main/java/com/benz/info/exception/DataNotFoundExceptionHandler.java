package com.benz.info.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.benz.info.model.ErrorMessage;

@RestControllerAdvice
public class DataNotFoundExceptionHandler {

	
	@ExceptionHandler
	public ResponseEntity<ErrorMessage> getErrorMessage(DataNotFoundException ex)
	{
		ErrorMessage errorMsg=new ErrorMessage(404,ex.getMessage(),"www.benz.com");
		
		return new ResponseEntity<ErrorMessage>(errorMsg,HttpStatus.NOT_FOUND);
	}
}
