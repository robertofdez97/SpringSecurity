package com.example.demo.service;

import com.example.demo.model.User;

public interface IUserService {

	public User save(User user);
	
	public User getByEmail(String email);
	
}
