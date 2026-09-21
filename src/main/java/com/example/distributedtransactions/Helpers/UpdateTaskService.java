package com.example.distributedtransactions.Helpers;

import com.example.distributedtransactions.Entity.TaskEntity;
import com.example.distributedtransactions.Repository.TaskRepository;
import com.example.distributedtransactions.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.distributedtransactions.Utils.CommonConstants.Invalid_Payload;

@Component
@Service
public class UpdateTaskService {
    @Autowired
    private TaskRepository taskRepository;

    //create//update//retrieve//delete
    public TaskEntity updateTask(String payload, Long id, String status, String taskType, Integer retries) {
        Utils utils = new Utils();
        String check = utils.checkName(payload);

        if (!check.equalsIgnoreCase(Invalid_Payload)) {
            TaskEntity task = new TaskEntity();
            task.setPayload(payload);
            task.setStatus(status);
            task.setTaskType(taskType);
            task.setRetries(retries);
            task.setCreatedAt(LocalDateTime.now());
            task.setUpdatedAt(LocalDateTime.now());
            System.out.println("updateTask");
            return taskRepository.save(task);
        }
        return null;

    }


}
