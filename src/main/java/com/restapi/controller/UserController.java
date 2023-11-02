package com.restapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.dto.UserDto;
import com.restapi.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

	private UserService userService;

	// build create User REST API
	@PostMapping()
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
		UserDto savedUser = userService.createUser(user);

		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	// build get User by Id REST API
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
		UserDto user = userService.getUserById(userId);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	// build get All Users REST API
	@GetMapping()
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.getAllUsers();
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	// build update User REST API
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDto user) {
		user.setId(userId);
		UserDto updatedUser = userService.updateUser(user);
		
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	// build delete User by Id REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
		userService.deleteUser(userId);
		
		return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
	}
}
