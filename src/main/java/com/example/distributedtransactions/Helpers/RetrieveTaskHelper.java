package com.example.distributedtransactions.Helpers;

import com.example.distributedtransactions.Entity.TaskEntity;
import com.example.distributedtransactions.Repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class RetrieveTaskHelper {
    private static final Logger log = LoggerFactory.getLogger(RetrieveTaskHelper.class);
    @Autowired
    TaskRepository taskRepository;

    public TaskEntity getTask(Long id) {

        log.info("Retreiving Task from Database by id {}",id);
        TaskEntity task = taskRepository.findById(id).orElse(null);

        return task;

    }
}
