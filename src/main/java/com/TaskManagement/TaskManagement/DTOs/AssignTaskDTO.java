package com.TaskManagement.TaskManagement.DTOs;

import java.util.UUID;

public record AssignTaskDTO(
    String username,
    UUID taskId) {
}
