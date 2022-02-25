package com.example.demo.model;

import java.util.Map;

import lombok.Data;

@Data
public class GoogleUser extends OAuth2UserInfo {

	@Override
	public String getId() {
		return (String) attributes.get("sub");
	}

	@Override
	public String getName() {
		return (String) attributes.get("name");
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("email");
	}

	@Override
	public String getImageUrl() {
		return (String) attributes.get("picture");
	}

	public GoogleUser(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
}
