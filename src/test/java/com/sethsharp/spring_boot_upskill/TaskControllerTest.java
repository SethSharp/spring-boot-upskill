package com.sethsharp.spring_boot_upskill;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.sethsharp.spring_boot_upskill.entity.Task;
import com.sethsharp.spring_boot_upskill.entity.User;
import com.sethsharp.spring_boot_upskill.repository.TaskRepository;
import com.sethsharp.spring_boot_upskill.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void itListsTasks() throws Exception {
        User user = userRepository.save(new User("Johnny"));
        taskRepository.save(new Task(user, "Some Task"));

        mockMvc.perform(get("/api/tasks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Some Task"));
    }

    @Test
    void itCreatesATask() throws Exception {
        User user = userRepository.save(new User("Johnny"));

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\": " + user.getId() + ", \"title\": \"New task\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title").value("New task"))
            .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    void itValidatesTitle() throws Exception {
        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"\"}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void itValidatesUserIdExists() throws Exception {
        assertThrows(Exception.class, () -> 
            mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\": 999, \"title\": \"New task\"}"))
        );
    }

    @Test
    void itValidatesUserId() throws Exception {
        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\": \"\", \"title\": \"New task\"}"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void itDeletesATask() throws Exception {
        User user = userRepository.save(new User("Johnny"));
        Task task = taskRepository.save(new Task(user, "To delete"));

        mockMvc.perform(delete("/api/tasks/" + task.getId()))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/tasks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isEmpty());
    }
}
