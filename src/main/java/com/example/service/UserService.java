package com.example.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.model.User;
import com.example.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto);
	public User findbymail(String username);
	
}
