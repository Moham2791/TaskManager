package com.example.distributedtransactions.Helpers;

import com.example.distributedtransactions.Entity.TaskEntity;
import com.example.distributedtransactions.Repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RetrieveTaskHelper {
    private static final Logger log = LoggerFactory.getLogger(RetrieveTaskHelper.class);
    @Autowired
    TaskRepository taskRepository;

    public TaskEntity getTask(Long id) {

        log.info("Retreiving Task from Database by id {}", id);
        TaskEntity task = taskRepository.findById(id).orElse(null);

        return task;

    }
    @Transactional
    public TaskEntity  getFirstPendingTask() {
        log.info("Retreiving Task from Database");
        return  taskRepository.findFirstPendingTask();
    }
}
