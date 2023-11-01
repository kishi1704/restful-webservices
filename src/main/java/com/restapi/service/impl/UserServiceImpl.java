package com.restapi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.restapi.dto.UserDto;
import com.restapi.entity.User;
import com.restapi.mapper.UserMapper;
import com.restapi.repository.UserRepository;
import com.restapi.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {

		// convert UserDto into User JPA Entity
		User user = UserMapper.mapToUser(userDto);
		
		User savedUser = userRepository.save(user);
		
		// convert User JPA Entity into UserDto
		UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
		
		return savedUserDto;
	}

	@Override
	public UserDto getUserById(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.get();
		
		// convert User JPA Entity into UserDto
		UserDto userDto = UserMapper.mapToUserDto(user);
		
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		
		return users.stream().map(UserMapper::mapToUserDto)
				.collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto user) {
		User existingUser = userRepository.findById(user.getId()).get();
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());

		User updatedUser = userRepository.save(existingUser);
		
		// convert User JPA Entity into UserDto
		UserDto updatedUserDto = UserMapper.mapToUserDto(updatedUser);

		return updatedUserDto;
	}

	@Override
	public void deleteUser(Long userId) {

		userRepository.deleteById(userId);
	}

}
