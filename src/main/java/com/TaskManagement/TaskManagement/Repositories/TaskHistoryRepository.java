package com.TaskManagement.TaskManagement.Repositories;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.TaskManagement.TaskManagement.Models.TaskHistory;

public interface TaskHistoryRepository extends ListCrudRepository<TaskHistory, UUID>{

}
