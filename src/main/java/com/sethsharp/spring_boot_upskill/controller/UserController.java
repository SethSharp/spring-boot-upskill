package com.sethsharp.spring_boot_upskill.controller;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.sethsharp.spring_boot_upskill.dto.CreateUserRequest;
import com.sethsharp.spring_boot_upskill.service.UserService;
import com.sethsharp.spring_boot_upskill.entity.User;

@RestController
@RequestMapping("/api/users")
public class UserController {

	public final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public User store(@Valid @RequestBody CreateUserRequest request) {
		return userService.save(request.getName());
	}
}