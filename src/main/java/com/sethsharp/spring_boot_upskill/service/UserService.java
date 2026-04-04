package com.sethsharp.spring_boot_upskill.service;

import org.springframework.stereotype.Service;
import com.sethsharp.spring_boot_upskill.repository.UserRepository;
import com.sethsharp.spring_boot_upskill.entity.User;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User save(String name) {
		return userRepository.save(new User(name));
	}
}