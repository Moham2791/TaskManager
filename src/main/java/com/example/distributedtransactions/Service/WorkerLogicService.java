package com.example.distributedtransactions.Service;

import com.example.distributedtransactions.Entity.TaskEntity;
import com.example.distributedtransactions.Helpers.CreateTaskHelper;
import com.example.distributedtransactions.Helpers.DeleteTaskHelper;
import com.example.distributedtransactions.Helpers.RetrieveTaskHelper;
import com.example.distributedtransactions.Helpers.UpdateTaskHelper;
import com.example.distributedtransactions.Repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkerLogicService {

    private static final Logger log = LoggerFactory.getLogger(WorkerLogicService.class);
    @Autowired
    CreateTaskHelper createTaskHelper;
    @Autowired
    DeleteTaskHelper deleteTaskHelper;
    @Autowired
    RetrieveTaskHelper retrieveTaskHelper;
    @Autowired
    UpdateTaskHelper updateTaskHelper;
    @Autowired
    TaskRepository taskRepository;


    public TaskEntity createTask(String payload, Long id, String status, String taskType, Integer retries) {
        log.info("Retreiving Task from Database");
        TaskEntity task = retrieveTaskHelper.getTask(id);
        if (task == null) {
            log.info("Task with id {} doesn't exist, creating a new task ", id);
            return createTaskHelper.createTask(payload, id, status, taskType, retries);
        } else {

            log.info("Task with id {} already exist, creating a new task ", id);
            return createTaskHelper.createTask(payload, id, status, taskType, retries);
        }


    }
}

