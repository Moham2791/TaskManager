package com.example.distributedtransactions.Service;

import org.springframework.scheduling.config.Task;

@org.springframework.stereotype.Service
public class DeleteTaskService {


    public Task deleteTask(String payload) {
// deleteTask should first call the retrieve Task Function and if the task exists then it should delete it based on
        // the status

        // if the task exists then delete it and if the task doesnot exist then automatically treat it as deleted.

        Task deleteTask = null;
        return deleteTask;

    }


}
