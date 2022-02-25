package com.example.demo.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exceptions.RestHandlerException;
import com.example.demo.model.ErrorResponse;

@RestControllerAdvice
public class SecurityRestHandlerException extends RestHandlerException{

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED, value = HttpStatus.UNAUTHORIZED)
	public ErrorResponse BadCredentialsException(HttpServletRequest req, HttpServletResponse res, BadCredentialsException e) {
		LOG.error("EndpointError: " + req.getPathInfo());
		return ErrorResponse.builder()
				.date(new Date())
				.errorMessage(e.getMessage())
				.status(HttpStatus.UNAUTHORIZED)
				.build();
	}
	
}
