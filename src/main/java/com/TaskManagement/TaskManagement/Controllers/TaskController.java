package com.TaskManagement.TaskManagement.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @GetMapping("")
    public ResponseEntity<String> testAuth() {
        return new ResponseEntity<String>("Ok", HttpStatus.OK);
    }
}
