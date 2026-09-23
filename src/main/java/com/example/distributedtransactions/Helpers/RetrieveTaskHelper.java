package com.example.distributedtransactions.Helpers;

import com.example.distributedtransactions.Entity.TaskEntity;
import com.example.distributedtransactions.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class RetrieveTaskHelper {
    @Autowired
    TaskRepository taskRepository;

    public TaskEntity getTask(Long id) {

        TaskEntity task = taskRepository.findById(id).get();

        return task;

    }
}
