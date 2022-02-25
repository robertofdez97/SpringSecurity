package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ConfigurationProperties(prefix = "spring.app")
@Data
@AllArgsConstructor
public class AppProperties {

	private final Auth auth = new Auth();
	private final OAuth2 oauth2 = new OAuth2();
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Auth {
		private String tokenSecret;
		private long tokenExpirationMsec;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static final class OAuth2 {
		private List<String> authorizedRedirectUris = new ArrayList<>();
	}
}
