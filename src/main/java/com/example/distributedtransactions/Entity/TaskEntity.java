package com.example.distributedtransactions.Entity;

import jakarta.persistence.*;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "tasks")
public class TaskEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String payload;
    public String status;
    public String taskType;
    public Integer retries;
    public String errorMessage;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    //Getters
    public TaskEntity() {

    }

    public Long getId() {
        return id;
    }

    public String getPayload() {
        return payload;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getStatus() {
        return status;
    }

    public Integer getRetries() {
        return retries;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    //setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}

