package com.restapi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.restapi.dto.UserDto;
import com.restapi.entity.User;
import com.restapi.exception.EmailAlreadyExistsException;
import com.restapi.exception.ResourceNotFoundException;
import com.restapi.repository.UserRepository;
import com.restapi.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		// check if email already exists
		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
		if(optionalUser.isPresent()) {
			throw new EmailAlreadyExistsException("Email Already Exists for User");
		}
		
		// convert UserDto into User JPA Entity
		// User user = UserMapper.mapToUser(userDto);
		User user = modelMapper.map(userDto, User.class);
		
		User savedUser = userRepository.save(user);
		
		// convert User JPA Entity into UserDto
		// UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
		UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);
		
		return savedUserDto;
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", userId)
		);
		
		// convert User JPA Entity into UserDto
		// UserDto userDto = UserMapper.mapToUserDto(user);
		UserDto userDto = modelMapper.map(user, UserDto.class);
		
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		
		// return users.stream().map(UserMapper::mapToUserDto)
		//		.collect(Collectors.toList());
		
		return users.stream().map((user) -> modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto user) {
		User existingUser = userRepository.findById(user.getId()).orElseThrow(
						() -> new ResourceNotFoundException("User", "id", user.getId())
		);
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());

		User updatedUser = userRepository.save(existingUser);
		
		// convert User JPA Entity into UserDto
		// UserDto updatedUserDto = UserMapper.mapToUserDto(updatedUser);
		UserDto updatedUserDto = modelMapper.map(updatedUser, UserDto.class);

		return updatedUserDto;
	}

	@Override
	public void deleteUser(Long userId) {

		User existingUser = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", "id", userId)
		);
		
		userRepository.deleteById(userId);
	}

}
