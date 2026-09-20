package com.example.distributedtransactions.Builder;

import com.example.distributedtransactions.Entity.Task;

public class taskBuilder {

    String taskName;
    long taskId;
    String taskType;
    String taskStatus;


    public Task taskBuilder(String taskName, long taskId, String taskType, String taskStatus) {
        Task buildTask = new Task();
        buildTask.payload = taskName;
        buildTask.id = taskId;
        buildTask.taskType = taskType;
        buildTask.status = taskStatus;
        return buildTask;
    }
}
