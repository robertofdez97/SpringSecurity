package com.example.demo.model;

import java.util.Map;

import javax.security.sasl.AuthenticationException;

public class OAuthUserFactory {
	
	public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) throws AuthenticationException {
	        if(registrationId.equalsIgnoreCase(Provider.GOOGLE.toString())) {
	            return new GoogleUser(attributes);
	        } else {
	            throw new AuthenticationException("Sorry! Login with " + registrationId + " is not supported yet.");
	        }
	    }
}
