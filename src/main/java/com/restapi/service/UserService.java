package com.restapi.service;

import java.util.List;

import com.restapi.entity.User;

public interface UserService {
	
	User createUser(User user);
	
	User getUserById(Long userId);
	
	List<User> getAllUsers();
}
