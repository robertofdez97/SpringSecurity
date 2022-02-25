package com.example.demo.exceptions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.configuration.ApiErrorProperties;
import com.example.demo.model.ErrorResponse;


@RestControllerAdvice
public class RestHandlerException {
	
	protected static final Logger LOG = LogManager.getLogger(RestHandlerException.class.getName());
	
	@Autowired
	private ApiErrorProperties apiErrorProperties;
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND, value = HttpStatus.NOT_FOUND)
	public ErrorResponse NotFoundException(HttpServletRequest req, HttpServletResponse res, NotFoundException e){
		
		LOG.error("EndpointError: " + req.getPathInfo());
		return ErrorResponse.builder()
				.date(new Date())
				.errorMessage(e.getErrorMessage())
				.status(HttpStatus.NOT_FOUND)
				.build();
	}
	
	@ExceptionHandler(value = {Exception.class})
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse runTimeException(HttpServletRequest req, HttpServletResponse res, Exception e){
		LOG.error("EndpointError: " + req.getPathInfo());
		//LOG.error("Exception stack: ", e);
		return ErrorResponse.builder()
				.date(new Date())
				.errorMessage(apiErrorProperties.getGeneralError())
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();
	}
	
	@ExceptionHandler(InsertException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT, value = HttpStatus.CONFLICT)
	public ErrorResponse insertException(HttpServletRequest req, HttpServletResponse res, NotFoundException e){
		
		LOG.error("EndpointError: " + req.getPathInfo());
		return ErrorResponse.builder()
				.date(new Date())
				.errorMessage(e.getErrorMessage())
				.status(HttpStatus.CONFLICT)
				.build();
	}
	
	
	
}
