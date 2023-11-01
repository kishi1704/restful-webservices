package com.restapi.service;

import java.util.List;

import com.restapi.dto.UserDto;
import com.restapi.entity.User;

public interface UserService {
	
	UserDto createUser(UserDto user);
	
	UserDto getUserById(Long userId);
	
	List<User> getAllUsers();
	
	User updateUser(User user);
	
	void deleteUser(Long userId);
}
