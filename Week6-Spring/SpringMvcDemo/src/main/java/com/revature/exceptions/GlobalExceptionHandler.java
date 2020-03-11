package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Parameter provided could not be parsed")
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public void handleMethodArgumentTypeMismatchException() {
		System.out.println("MethodArgumentTypeMismatchException thrown");
	}

}
