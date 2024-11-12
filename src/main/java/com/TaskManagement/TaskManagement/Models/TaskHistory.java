package com.TaskManagement.TaskManagement.Models;

import java.time.LocalDateTime;
import java.util.UUID;

import com.TaskManagement.TaskManagement.Constants.State;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class TaskHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
    
    @ManyToOne
    @JoinColumn(name = "updated_by", nullable = false)
    private AppUser updatedBy;
    
    @Enumerated(EnumType.STRING)
    private State oldState;
    
    @Enumerated(EnumType.STRING)
    private State newState;
    
    private LocalDateTime timestamp;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public AppUser getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(AppUser updatedBy) {
        this.updatedBy = updatedBy;
    }

    public State getOldState() {
        return oldState;
    }

    public void setOldState(State oldState) {
        this.oldState = oldState;
    }

    public State getNewState() {
        return newState;
    }

    public void setNewState(State newState) {
        this.newState = newState;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TaskHistory [id=" + id + ", task=" + task + ", updatedBy=" + updatedBy + ", oldState=" + oldState
                + ", newState=" + newState + ", timestamp=" + timestamp + "]";
    }

    
}