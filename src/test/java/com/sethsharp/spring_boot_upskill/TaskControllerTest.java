package com.sethsharp.spring_boot_upskill;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
    }

    @Test
    void itListsTasks() throws Exception {
        taskRepository.save(new Task("Some Task"));

        mockMvc.perform(get("/api/tasks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].title").value("Some Task"));
    }

    @Test
    void itCreatesATask() throws Exception {
        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"New task\"}"))
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
    void itDeletesATask() throws Exception {
        Task task = taskRepository.save(new Task("To delete"));

        mockMvc.perform(delete("/api/tasks/" + task.getId()))
            .andExpect(status().isOk());

        mockMvc.perform(get("/api/tasks"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isEmpty());
    }
}
