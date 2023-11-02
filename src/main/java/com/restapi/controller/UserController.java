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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(
		name = "CRUD REST APIs for User Resource",
		description = "CRUD REST APIs - Create User, Update User, Get User, Get All Users, Delete User"
)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

	private UserService userService;

	// build create User REST API
	@Operation(
			summary = "Create User REST API",
			description = "Create User REST API is used to save user in a database"
	)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP Status 201 CREATED"
	)
	@PostMapping()
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
		UserDto savedUser = userService.createUser(user);

		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	// build get User by Id REST API
	@Operation(
			summary = "Get User By ID REST API",
			description = "Get User By ID REST API is used to get a single user from the database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
		UserDto user = userService.getUserById(userId);

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	// build get All Users REST API
	@Operation(
			summary = "Get All Users REST API",
			description = "Get All Users REST API is used to get all users from the database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
	@GetMapping()
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.getAllUsers();
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	// build update User REST API
	@Operation(
			summary = "Update User REST API",
			description = "Update User REST API is used to update a particular user in the database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDto user) {
		user.setId(userId);
		UserDto updatedUser = userService.updateUser(user);
		
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	// build delete User by Id REST API
	@Operation(
			summary = "Delete User REST API",
			description = "Delete User REST API is used to delete a particular user from the database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
		userService.deleteUser(userId);
		
		return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
	}
}
