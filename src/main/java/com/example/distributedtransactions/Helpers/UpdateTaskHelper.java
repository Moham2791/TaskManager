package com.example.distributedtransactions.Helpers;

import com.example.distributedtransactions.Entity.TaskEntity;
import com.example.distributedtransactions.Repository.TaskRepository;
import com.example.distributedtransactions.Utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.distributedtransactions.Utils.CommonConstants.Invalid_Payload;

@Component
@Service
public class UpdateTaskHelper {
    private static final Logger log = LoggerFactory.getLogger(UpdateTaskHelper.class);
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
            log.info("Task Updated with Id : " + id);
            return taskRepository.save(task);
        }
        return null;

    }


}
