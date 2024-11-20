package com.TaskManagement.TaskManagement.DTOs;

import java.util.UUID;

import com.TaskManagement.TaskManagement.Constants.State;

public record UpdateTaskDTO(
    State state,
    UUID taskId) {
}
