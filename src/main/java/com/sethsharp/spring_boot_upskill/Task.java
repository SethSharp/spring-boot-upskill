package com.sethsharp.spring_boot_upskill;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Title is required")
	@Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters")
	private String title;

	private boolean completed;

	public Task() {}

	public Task(String title) {
		this.title = title;
		this.completed = false;
	}

	public Long getId() { return id; }
	public String getTitle() { return title; }
	public boolean isCompleted() { return completed; }
	public void setTitle(String title) { this.title = title; }
	public void setCompleted(boolean completed) { this.completed = completed; }
}