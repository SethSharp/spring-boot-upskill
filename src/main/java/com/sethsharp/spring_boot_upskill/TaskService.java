package com.sethsharp.spring_boot_upskill;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	public Task save(Task task) {
		return taskRepository.save(task);
	}

	public Task update(Long id, Task task) {
		Task existing = taskRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Task not found."));

		existing.setTitle(task.getTitle());
		existing.setCompleted(task.isCompleted());
		
		return taskRepository.save(existing);
	}

	public void delete(Long id) {
		taskRepository.deleteById(id);
	}
}