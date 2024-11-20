package com.TaskManagement.TaskManagement.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement.TaskManagement.DTOs.AssignTaskDTO;
import com.TaskManagement.TaskManagement.DTOs.TaskWriteDTO;
import com.TaskManagement.TaskManagement.DTOs.UpdateTaskDTO;
import com.TaskManagement.TaskManagement.Services.TaskService;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("")
    public ResponseEntity<String> createTask(@RequestBody TaskWriteDTO taskWriteDTO) {
        return taskService.createTask(taskWriteDTO);
    }

    @PutMapping("")
    public ResponseEntity<String> assignTask(@RequestBody AssignTaskDTO assignTaskDTO) {
        return taskService.assignTask(assignTaskDTO.username(), assignTaskDTO.taskId());
    }

    @PatchMapping("")
    public ResponseEntity<String> updateState(@RequestBody UpdateTaskDTO updateTaskDTO) {
        return taskService.updateTask(updateTaskDTO.state(), updateTaskDTO.taskId());
    }
}
