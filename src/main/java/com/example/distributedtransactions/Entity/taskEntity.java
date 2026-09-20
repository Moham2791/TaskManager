package com.example.distributedtransactions.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class taskEntity {

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
