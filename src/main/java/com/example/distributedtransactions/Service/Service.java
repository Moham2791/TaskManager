package com.example.distributedtransactions.Service;

import com.example.distributedtransactions.Builder.taskBuilder;
import com.example.distributedtransactions.Repository.taskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private taskRepository taskRepository;

    //create//update//retrieve//delete
    public Task createTask(String payload) {
        taskBuilder create = new taskBuilder();
        Task newTask = new Task();

        return create;
    }

    public Task updateTask(String payload) {

        taskBuilder update = new taskBuilder();


        return buildTask;
    }

    public Task deleteTask(String payload) {


    }

    public Task retreiveTask(String payload) {


    }


}
