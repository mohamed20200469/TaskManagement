package com.TaskManagement.TaskManagement.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement.TaskManagement.DTOs.TaskWriteDTO;
import com.TaskManagement.TaskManagement.Services.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("")
    public ResponseEntity<String> testAuth(@RequestBody TaskWriteDTO taskWriteDTO) {
        return taskService.createTask(taskWriteDTO);
    }
}
