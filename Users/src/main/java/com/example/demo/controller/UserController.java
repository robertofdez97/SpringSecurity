package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.IUserService;


@RestController()
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping("/getByEmail")
	public User getUserByEmail(@RequestParam() String email) {
		return userService.getByEmail(email);
	}
	
	@PostMapping("/save")
	public User saveUser(@RequestBody User user) {
		User user2 = userService.save(user);
		return user2;
	}
	
}
