package com.sethsharp.spring_boot_upskill.entity;

import jakarta.persistence.*;
import java.util.List;
import com.sethsharp.spring_boot_upskill.entity.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	public User() {}

	public User(String name) {
		this.name = name;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Task> tasks;

	public Long getId() { return id; }
	public String getName() { return name; }
	@JsonIgnore
	public List<Task> getTasks() { return tasks; }
}