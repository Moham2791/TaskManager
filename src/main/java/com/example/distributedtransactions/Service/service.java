package com.example.distributedtransactions.Service;

import com.example.distributedtransactions.Repository.taskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;

@org.springframework.stereotype.Service
public class service {
@Autowired
private taskRepository taskRepository;

public Task submitTask(String payload ){

    Task task = new Task(taskRepo);
    task.set



}



}
