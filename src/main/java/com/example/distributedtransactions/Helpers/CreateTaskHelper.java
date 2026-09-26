package com.example.distributedtransactions.Helpers;

import com.example.distributedtransactions.Entity.TaskEntity;
import com.example.distributedtransactions.Properties.TaskQueueProperties;
import com.example.distributedtransactions.Repository.TaskRepository;
import com.example.distributedtransactions.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.distributedtransactions.Utils.CommonConstants.Invalid_Payload;

@Component
@Service
public class CreateTaskHelper {
    @Autowired
    private TaskRepository taskRepository;
    //create//update//retrieve//delete
    @Autowired
    private TaskQueueProperties taskQueueProps;

    // create task with all the required feilds used by controller method to send a reques to the DB
    public TaskEntity createTask(String payload, Long id, String status, String taskType, Integer retries) {
        Utils utils = new Utils();
        String check = utils.checkName(payload);
        if (!check.equalsIgnoreCase(Invalid_Payload)) {
       try{     TaskEntity task = new TaskEntity();
            task.setId(id);
            task.setPayload(payload);
            task.setStatus(status);
            task.setTaskType(taskType);
            task.setRetries(retries);
            task.setCreatedAt(LocalDateTime.now());
            task.setUpdatedAt(LocalDateTime.now());

            return taskRepository.save(task);}
       catch (DataIntegrityViolationException e) {

       }
        }
        return null;

    }


}







