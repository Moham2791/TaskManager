package com.example.distributedtransactions.Service;

import com.example.distributedtransactions.Repository.taskRepository;
import com.example.distributedtransactions.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.distributedtransactions.Utils.CommonConstants.Invalid_Payload;

@org.springframework.stereotype.Service
public class CreateTaskService {

    //create//update//retrieve//delete
    public com.example.distributedtransactions.Entity.Task createTask(String payload, Long id, String status, String taskType) {
        Utils utils = new Utils();
        String check = utils.checkName(payload);
        com.example.distributedtransactions.Entity.Task task = new com.example.distributedtransactions.Entity.Task();
        if (!check.equals(Invalid_Payload)) {

            task.payload = payload;
            task.id = id;
            task.status = status;
            task.taskType = taskType;

            return task;
        }
        return task;

    }


}







