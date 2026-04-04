package com.sethsharp.spring_boot_upskill;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	public final TaskRepository taskRepository;

	public TaskController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@GetMapping
	public List<Task> index() {
		return taskRepository.findAll();
	}

	@PostMapping
	public Task store(@Valid @RequestBody Task task) {
		return taskRepository.save(task);
	}

	@PutMapping("/{id}")
	public Task update(@PathVariable Long id, @RequestBody Task task) {
		Task existing = taskRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Task not found."));

		existing.setTitle(task.getTitle());
		existing.setCompleted(task.isCompleted());
		
		return taskRepository.save(existing);
	}

	@DeleteMapping("/{id}")
	public void destroy(@PathVariable Long id) {
		taskRepository.deleteById(id);
	}
}