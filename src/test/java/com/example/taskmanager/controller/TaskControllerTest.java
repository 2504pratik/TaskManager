package com.example.taskmanager.controller;

import com.example.taskmanager.model.Task;
import com.example.taskmanager.model.TaskStatus;
import com.example.taskmanager.security.CustomUserDetailsService;
import com.example.taskmanager.security.JwtTokenUtil;
import com.example.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
@WithMockUser(username = "testuser", roles = "USER")
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void testGetAllTasks() throws Exception {
        List<Task> tasks = Arrays.asList(
                new Task(1L, "Task 1", "Description 1", LocalDate.now().plusDays(5), TaskStatus.PENDING, null, null),
                new Task(2L, "Task 2", "Description 2", LocalDate.now().plusDays(10), TaskStatus.COMPLETED, null, null)
        );

        Mockito.when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Task 1"))
                .andExpect(jsonPath("$[0].status").value("PENDING"));
    }

    @Test
    void testGetTaskById() throws Exception {
        Task task = new Task(1L, "Task 1", "Description 1", LocalDate.now().plusDays(5), TaskStatus.PENDING, null, null);

        Mockito.when(taskService.getTaskById(1L)).thenReturn(task);

        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Task 1"))
                .andExpect(jsonPath("$.dueDate").value(LocalDate.now().plusDays(5).toString()));
    }

    @Test
    void testGetTaskById_NotFound() throws Exception {
        Mockito.when(taskService.getTaskById(1L)).thenReturn(null);

        mockMvc.perform(get("/tasks/1"))
                .andExpect(status().isNotFound());
    }
}