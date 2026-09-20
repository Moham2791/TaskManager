package com.example.distributedtransactions.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String payload;
    @Column(nullable = false)
    private String status;
    private Integer retries;
    private String errorMessage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //Getters/Setters
}
