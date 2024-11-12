package com.TaskManagement.TaskManagement.Repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.TaskManagement.TaskManagement.Models.Task;

public interface TaskRepository extends ListCrudRepository<Task, UUID>{

}
