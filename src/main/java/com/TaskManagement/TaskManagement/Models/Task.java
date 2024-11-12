package com.TaskManagement.TaskManagement.Models;

import java.util.List;
import java.util.UUID;

import com.TaskManagement.TaskManagement.Constants.State;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Task {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State state = State.OPEN;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private AppUser createdBy;
    
    @ManyToOne
    @JoinColumn(name = "assigned_to")
    private AppUser assignedTo;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskHistory> history;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public AppUser getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(AppUser createdBy) {
        this.createdBy = createdBy;
    }

    public AppUser getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(AppUser assignedTo) {
        this.assignedTo = assignedTo;
    }

    public List<TaskHistory> getHistory() {
        return history;
    }

    public void setHistory(List<TaskHistory> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return "Task [id=" + id + ", title=" + title + ", description=" + description + ", state=" + state
                + ", createdBy=" + createdBy + ", assignedTo=" + assignedTo + ", history=" + history + "]";
    }

    
}
