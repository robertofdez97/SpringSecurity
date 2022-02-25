package com.example.demo.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotFoundException extends NotFoundException{

	public UserNotFoundException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3444057975648904390L;
}
