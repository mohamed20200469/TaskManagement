package com.TaskManagement.TaskManagement.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.TaskManagement.TaskManagement.DTOs.TaskWriteDTO;
import com.TaskManagement.TaskManagement.Models.Task;
import com.TaskManagement.TaskManagement.Repositories.TaskRepository;
import com.TaskManagement.TaskManagement.Repositories.UserRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> createTask(TaskWriteDTO taskWriteDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found!");
        }
    
        User user = (User) authentication.getPrincipal();
        var appUser = userRepository.findByUsername(user.getUsername());
    
        return appUser.map(u -> {
            Task task = new Task();
            task.setCreatedBy(u);
            task.setDescription(taskWriteDTO.description());
            task.setTitle(taskWriteDTO.title());
    
            Task savedTask = taskRepository.save(task);
            return ResponseEntity.ok(savedTask.toString());
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!"));
    }
    
}
