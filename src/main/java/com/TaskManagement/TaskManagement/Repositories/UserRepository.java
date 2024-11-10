package com.TaskManagement.TaskManagement.Repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.TaskManagement.TaskManagement.Models.AppUser;

public interface UserRepository extends ListCrudRepository<AppUser, UUID>{
    Optional<AppUser> findByUsername(String username);

    boolean existsByUsername(String username);
}
