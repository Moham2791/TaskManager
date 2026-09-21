package com.example.distributedtransactions.Service;

import com.example.distributedtransactions.Builder.taskBuilder;
import com.example.distributedtransactions.Entity.Task;

@org.springframework.stereotype.Service
public class UpdateTaskService {


    //create//update//retrieve//delete
    public Task updateTask(String payload, String id) {

        taskBuilder updateTask = new taskBuilder();
        RetrieveTaskService retrieveTaskService = new RetrieveTaskService();
        com.example.distributedtransactions.Entity.Task updatedTask = new com.example.distributedtransactions.Entity.Task();

        com.example.distributedtransactions.Entity.Task getTask = retrieveTaskService.retreiveTask(id);

        if (getTask != null) {
            //write logic about what the task is going to be and how to write and what properties are going to be
            //Update the task
            //Make Update call to the DataBase
        }
        return updatedTask;


    }


}
