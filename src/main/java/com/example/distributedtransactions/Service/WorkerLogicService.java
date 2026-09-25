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

import static com.example.distributedtransactions.Utils.CommonConstants.*;

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

    public void processTask(String payload, Long id, String status, String taskType, Integer retries) {

        //@Scheduled polls DB for first PENDING unlocked task
        //If thread pool has capacity, submit to @Async worker
        //If thread pool is full, skip — task stays PENDING in DB
        //Next schedule cycle picks it up
        //Worker locks row, processes, updates status to DONE, releases loc



    }


}

