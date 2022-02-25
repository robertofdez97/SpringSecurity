package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "urls")
//@PropertySource(value = "clientConfig.yaml")
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientConfig {
	private String user;

}
