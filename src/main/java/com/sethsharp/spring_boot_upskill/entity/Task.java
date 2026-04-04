package com.sethsharp.spring_boot_upskill.entity;

import jakarta.persistence.*;
import com.sethsharp.spring_boot_upskill.entity.User;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String title;

	private boolean completed;

	public Task() {}

	public Task(User user, String title) {
		this.user = user;
		this.title = title;
		this.completed = false;
	}

	public Long getId() { return id; }
	public String getTitle() { return title; }
	public boolean isCompleted() { return completed; }
	public void setTitle(String title) { this.title = title; }
	public void setCompleted(boolean completed) { this.completed = completed; }
}