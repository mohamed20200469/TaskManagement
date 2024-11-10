package com.TaskManagement.TaskManagement.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagement.TaskManagement.DTOs.UserWriteDTO;
import com.TaskManagement.TaskManagement.Services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody UserWriteDTO userWriteDTO) {
        return userService.register(userWriteDTO);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody UserWriteDTO userWriteDTO) {
        return userService.login(userWriteDTO);
    }
}
