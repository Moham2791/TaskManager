package com.example.distributedtransactions.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface taskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(String name);
}
