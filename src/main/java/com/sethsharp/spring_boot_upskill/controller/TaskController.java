package com.sethsharp.spring_boot_upskill.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import jakarta.validation.Valid;
import com.sethsharp.spring_boot_upskill.dto.CreateTaskRequest;
import com.sethsharp.spring_boot_upskill.entity.Task;
import com.sethsharp.spring_boot_upskill.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	public final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping
	public List<Task> index() {
		return taskService.findAll();
	}

	@PostMapping
	public Task store(@Valid @RequestBody CreateTaskRequest request) {
		return taskService.save(new Task(request.getTitle()));
	}

	@PutMapping("/{id}")
	public Task update(@PathVariable Long id, @RequestBody Task task) {
		return taskService.update(id, task);
	}

	@DeleteMapping("/{id}")
	public void destroy(@PathVariable Long id) {
		taskService.delete(id);
	}
}