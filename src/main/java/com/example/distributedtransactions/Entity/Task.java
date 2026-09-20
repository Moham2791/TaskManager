package com.example.distributedtransactions.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String payload;
    public String status;
    public String taskType;
    public Integer retries;
    public String errorMessage;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;

    //Getters/Setters

    public Task(){

    }
}
