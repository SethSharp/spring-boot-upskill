package com.sethsharp.spring_boot_upskill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sethsharp.spring_boot_upskill.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
}