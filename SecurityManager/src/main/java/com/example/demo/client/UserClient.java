package com.example.demo.client;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.config.ClientConfig;
import com.example.demo.model.User;

@Service
public class UserClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ClientConfig clientConfig;
	
	public Optional<User> getUserByEmail(String email){
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		Map<String,String> params = Map.of("email", email);
      //  User result = restTemplate.getForObject("http://localhost:8090" + "/user/getByEmail", User.class, params);
		
		String uriTemplate = UriComponentsBuilder.fromHttpUrl("http://localhost:8090" + "/user/getByEmail")
				.queryParam("email", "{email}")
				.encode()
				.toUriString();
		
		ResponseEntity<User> response = restTemplate.exchange(uriTemplate, HttpMethod.GET, entity, User.class, params);
		
		if(response.getStatusCode().isError() || !response.hasBody()) return Optional.empty();
		return Optional.of(response.getBody());
		
	}

	public Optional<User> save(User user) {
		ResponseEntity<User> response = restTemplate.postForEntity("http://localhost:8090" + "/user/save", user, User.class);
		if(response.getStatusCode().isError() || !response.hasBody() ) return null;
		return Optional.of(response.getBody());
	}
}
