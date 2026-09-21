package com.example.distributedtransactions.Service;

@org.springframework.stereotype.Service
public class RetrieveTaskService {


    public com.example.distributedtransactions.Entity.Task retreiveTask(String id) {

// Retrieve Task is always called to get the task and its associated data
        return queryTaskById(id);
    }

    public com.example.distributedtransactions.Entity.Task queryTaskById(String id) {
        com.example.distributedtransactions.Entity.Task task = new com.example.distributedtransactions.Entity.Task();
        return task;
    }
}
