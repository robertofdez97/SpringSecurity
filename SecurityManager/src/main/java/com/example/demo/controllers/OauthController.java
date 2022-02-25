package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.client.UserClient;
import com.example.demo.model.GenericJson;
import com.example.demo.model.LoginRequest;
import com.example.demo.service.TokenService;

@RestController
@RequestMapping("/oauth")
public class OauthController {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserClient userClient;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/signIn")
	public GenericJson<String> login(@RequestBody LoginRequest loginRequest){
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenService.createToken(authentication);
        return new GenericJson<String>(token);
	}
	

}
