package com.TaskManagement.TaskManagement.Repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.TaskManagement.TaskManagement.Models.Role;

public interface RoleRepository extends ListCrudRepository<Role, UUID>{
    Optional<Role> findByName(String name);
}
