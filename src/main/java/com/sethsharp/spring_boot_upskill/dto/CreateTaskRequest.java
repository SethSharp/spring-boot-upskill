package com.sethsharp.spring_boot_upskill.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public class CreateTaskRequest {

	@NotNull(message = "User is required")
	private Long userId;

	@NotBlank(message = "Title is required")
	@Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters")
	private String title;

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	public Long getUserId() { return userId; }
	public void setUserId(Long userId) { this.userId = userId; }
}