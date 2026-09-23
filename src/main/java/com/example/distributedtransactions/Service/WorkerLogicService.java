package com.example.distributedtransactions.Service;

import com.example.distributedtransactions.Entity.TaskEntity;
import com.example.distributedtransactions.Helpers.CreateTaskHelper;
import com.example.distributedtransactions.Helpers.DeleteTaskHelper;
import com.example.distributedtransactions.Helpers.RetrieveTaskHelper;
import com.example.distributedtransactions.Helpers.UpdateTaskHelper;
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

    public void workerLogic(String payload, Long id, String status, String taskType, Integer retries) {
         // Workers will whenever pick a task they will lock the row so that other workers will not be able to pick up the same task causing rework.
        // Polling should be used by the service to figure out if there is a new task in the database
        log.info("workerLogic start");
        log.info("Retreiving Task from Database");

        TaskEntity task = retrieveTaskHelper.getTask(id);
        if (task == null) {
            log.info("Task with id {} doesn't exist, creating a new task ", id);
            createTaskHelper.createTask(payload, id, status, taskType, retries);
        } else {
            //if the task exists get the status of the task and decide whether to update or leave it
            if (task.getStatus().equalsIgnoreCase(Ready)) {
                log.info("Task {} with id {} is has ready to be picked up ", payload, id);
                updateTaskHelper.updateTask(payload, id, In_Progress, taskType, retries);

            } else if (task.getStatus().equalsIgnoreCase(In_Progress)) {


                log.info("Task with id {} is has already been picked", id);

            } else if (task.getStatus().equalsIgnoreCase(Done)) {

                log.info("Task with id {} has been successfully completed. Moving to the next task", id);
                return;

            }

        }


    }
}
