package com.sethsharp.spring_boot_upskill.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.sethsharp.spring_boot_upskill.entity.Task;
import com.sethsharp.spring_boot_upskill.entity.User;
import com.sethsharp.spring_boot_upskill.repository.TaskRepository;
import com.sethsharp.spring_boot_upskill.repository.UserRepository;

@Service
public class TaskService {

	private final TaskRepository taskRepository;
	private final UserRepository userRepository;

	public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
	}

	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	public Task save(Long id, String title) {
		User user = userRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("User not found."));
		Task task = new Task(user, title);

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