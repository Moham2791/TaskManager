package com.example.distributedtransactions.Service;

import com.example.distributedtransactions.Entity.TaskEntity;
import com.example.distributedtransactions.Helpers.CreateTaskService;
import com.example.distributedtransactions.Helpers.DeleteTaskService;
import com.example.distributedtransactions.Helpers.RetrieveTaskService;
import com.example.distributedtransactions.Helpers.UpdateTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.distributedtransactions.Utils.CommonConstants.*;

@Service
public class WorkerLogic {

    private static final Logger log = LoggerFactory.getLogger(WorkerLogic.class);
    @Autowired
    CreateTaskService createTaskService;
    @Autowired
    DeleteTaskService deleteTaskService;
    @Autowired
    RetrieveTaskService retrieveTaskService;
    @Autowired
    UpdateTaskService updateTaskService;

    public void workerLogic(String payload, Long id, String status, String taskType, Integer retries) {
        // Check if a task exists in the dataBase and if it does then it should check the status of the task
        // if the task exists then it should update the task.
        // before updating it should be checked whether another worker is already working on the task
        // if another worker is already working on the task then it should drop and go to another task
        log.info("workerLogic start");
        log.info("Retreiving Task from Database");

        TaskEntity task = retrieveTaskService.getTask(id);
        if (task == null) {
            log.info("Task with id {} doesn't exist, creating a new task ", id);
            createTaskService.createTask(payload, id, status, taskType, retries);
        } else {

            if (task.getStatus().equalsIgnoreCase(Ready)) {
                log.info("Task {} with id {} is has ready to be picked up ", payload, id);
                updateTaskService.updateTask(payload, id, In_Progress, taskType, retries);

            } else if (task.getStatus().equalsIgnoreCase(In_Progress)) {

                log.info("Task with id {} is has already been picked", id);

            } else if (task.getStatus().equalsIgnoreCase(Done)) {

                log.info("Task with id {} has been successfully completed. Moving to the next task", id);
                return;

            }

        }


    }
}
