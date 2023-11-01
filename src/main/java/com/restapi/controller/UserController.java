package com.restapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.entity.User;
import com.restapi.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

	private UserService userService;

	// build create User REST API
	@PostMapping()
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userService.createUser(user);

		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	// build get User by Id REST API
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long userId) {
		User user = userService.getUserById(userId);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	// build get All Users REST API
	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
