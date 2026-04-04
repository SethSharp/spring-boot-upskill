package com.sethsharp.spring_boot_upskill;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import jakarta.validation.Valid;

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
	public Task store(@Valid @RequestBody Task task) {
		return taskService.save(task);
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