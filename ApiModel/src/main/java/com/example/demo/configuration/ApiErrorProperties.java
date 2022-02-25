package com.example.demo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "api.error.messages")
@Configuration(value = "apiErrorProperties")
public class ApiErrorProperties {

	private String generalError;
}
