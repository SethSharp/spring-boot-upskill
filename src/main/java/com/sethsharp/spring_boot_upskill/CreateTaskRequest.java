package com.sethsharp.spring_boot_upskill;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTaskRequest {

	@NotBlank(message = "Title is required")
	@Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters")
	private String title;

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
}