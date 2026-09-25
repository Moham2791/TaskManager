package com.example.distributedtransactions.Helpers;

import com.example.distributedtransactions.Entity.TaskEntity;
import com.example.distributedtransactions.Repository.TaskRepository;
import com.example.distributedtransactions.Utils.Utils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.distributedtransactions.Utils.CommonConstants.Invalid_Payload;
import static com.example.distributedtransactions.Utils.CommonConstants.TaskUpdated;

@Component
@Service
public class UpdateTaskHelper {
    private static final Logger log = LoggerFactory.getLogger(UpdateTaskHelper.class);
    @Autowired
    private TaskRepository taskRepository;

    //create//update//retrieve//delete
    public String updateTask(String payload, Long id, String status, String taskType, Integer retries) {
        Utils utils = new Utils();
        String check = utils.checkName(payload);

        if (!check.equalsIgnoreCase(Invalid_Payload)) {
            TaskEntity task = taskRepository.findById(id).orElse(null);
            task.setPayload(payload);
            task.setStatus(status);
            task.setTaskType(taskType);
            task.setRetries(retries);
            task.setCreatedAt(LocalDateTime.now());
            task.setUpdatedAt(LocalDateTime.now());
            log.info("Task Updated with Id : " + id);
            taskRepository.save(task);
            return TaskUpdated;
        }
        return null;

    }

    public String updatePickedTask_Retry(String payload, Long id, String status, String taskType, Integer retries) {
        if (retries == 0) {
            log.info("Retry limit exceeded for Task with Payload ={} and id={}. Please try again", payload, id);
            return "Retries Exceded,Please Retry Again";
        }
        retries -= 1;
        return updatePickedTask(payload, id, status, taskType, retries);
    }

    @Async
    @Transactional
    public String updatePickedTask(String payload, Long id, String status, String taskType, Integer retries) {
        TaskEntity task = taskRepository.findById(id).orElse(null);
        Utils utils = new Utils();
        String check = utils.checkName(payload);

        if (!check.equalsIgnoreCase(Invalid_Payload)) {

            task.setPayload(payload);
            task.setStatus(status);
            task.setTaskType(taskType);
            task.setCreatedAt(LocalDateTime.now());
            task.setUpdatedAt(LocalDateTime.now());
            log.info("Task Updated with Id : " + id);
            try {
                taskRepository.save(task);
            } catch (DataAccessException e) {
                log.info("Update Task Failed with Id={},payload={},Exception Details={} , IncidentDetails ={}  ", id, payload, e.getMessage(), e.getCause());
                return updatePickedTask_Retry(payload, id, status, taskType, retries);

            }
            return TaskUpdated;
        }
        return null;

    }


}
