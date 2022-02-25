package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.client.UserClient;
import com.example.demo.model.User;
import com.example.demo.model.UserPrincipal;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserClient userClient;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userClient.getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found email: " + email));
		return UserPrincipal.create(user);
	}

}
