package com.example.demo.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoggerFactory {
	
	
	
	public static Logger getKibanaLogger(Class<?> classType) {
		return LogManager.getLogger(classType);
	}
	
}
