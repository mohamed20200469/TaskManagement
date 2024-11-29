package com.TaskManagement.TaskManagement.Services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.TaskManagement.TaskManagement.Constants.State;
import com.TaskManagement.TaskManagement.DTOs.TaskWriteDTO;
import com.TaskManagement.TaskManagement.Models.Task;
import com.TaskManagement.TaskManagement.Models.TaskHistory;
import com.TaskManagement.TaskManagement.Repositories.TaskHistoryRepository;
import com.TaskManagement.TaskManagement.Repositories.TaskRepository;
import com.TaskManagement.TaskManagement.Repositories.UserRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskHistoryRepository taskHistoryRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, TaskHistoryRepository taskHistoryRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.taskHistoryRepository = taskHistoryRepository;
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

            taskRepository.save(task);
            return ResponseEntity.status(HttpStatus.CREATED).body("Task created successfully!");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!"));
    }
    
    public ResponseEntity<String> assignTask(String username, UUID taskId) {
        var appUser = userRepository.findByUsername(username);

        return appUser.map(u -> {
            var task = taskRepository.findById(taskId);
            if (task.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found!");
            }

            task.get().setAssignedTo(u);
            taskRepository.save(task.get());
            return ResponseEntity.ok("Task Updated successfully!");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!"));
    }

    public ResponseEntity<String> updateTask(State state, UUID taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found!");
        }

        User user = (User) authentication.getPrincipal();
        var appUser = userRepository.findByUsername(user.getUsername());

        return appUser.map(u -> {
            var task = taskRepository.findById(taskId);
            if (task.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found!");
            }

            if (!task.get().getAssignedTo().getId().equals(appUser.get().getId())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("This is not your task!");
            }
            
            
            TaskHistory taskHistory = new TaskHistory();
            taskHistory.setTask(task.get());
            taskHistory.setOldState(task.get().getState());
            taskHistory.setNewState(state);
            taskHistory.setTimestamp(LocalDateTime.now());
            taskHistory.setUpdatedBy(u);
            taskHistoryRepository.save(taskHistory);
            
            task.get().setState(state);
            taskRepository.save(task.get());
            return ResponseEntity.ok("Task Updated successfully!");
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!"));
    }
}
