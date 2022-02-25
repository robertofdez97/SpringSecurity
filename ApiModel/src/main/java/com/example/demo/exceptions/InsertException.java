package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 277039972851864816L;
	private String errorMessage;
}
