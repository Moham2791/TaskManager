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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static com.example.distributedtransactions.Utils.CommonConstants.*;

@Service
public class UpdateTaskHelper {
    private static final Logger log = LoggerFactory.getLogger(UpdateTaskHelper.class);
    @Autowired
    private TaskRepository taskRepository;

    @Async
    @Transactional
    public CompletableFuture<String> updatePickedTask(String payload, Long id, String status, String taskType, Integer retries) {
        TaskEntity task = taskRepository.findByIdWithLock(id).orElse(null);
        Utils utils = new Utils();
        String check = utils.checkName(task.getPayload());

        if (!check.equalsIgnoreCase(Invalid_Payload)) {
            task.setStatus(Done);
            task.setTaskType(taskType);
            task.setUpdatedAt(LocalDateTime.now());
            log.info("Task Updated with Id : " + id);
            try {
                log.info("Processing task Id={} payload={}", id, payload);
                Thread.sleep(Thread_Time_Millis);
                return utils.retry(retries, () -> {
                    taskRepository.save(task);
                    return CompletableFuture.completedFuture(TaskUpdated);
                });
            } catch (InterruptedException e) {
                log.error("Task interrupted Id={}", id);
            } catch (DataAccessException e) {
                log.info("Update Task Failed Id={} Details={}", id, e.getMessage());
                return utils.retry(retries, () -> updatePickedTask(payload, id, status, taskType, retries));
            }
            return CompletableFuture.completedFuture(TaskUpdated);
        }
        return CompletableFuture.completedFuture(Failed);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    // This method is un-used
    /*   //create//update//retrieve//delete
    public String updateTask(String payload, Long id, String status, String taskType, Integer retries) {
        Utils utils = new Utils();
        String check = utils.checkName(payload);

        if (!check.equalsIgnoreCase(Invalid_Payload)) {
            TaskEntity task = taskRepository.findById(id).orElse(null);
            task.setPayload(payload);
            task.setStatus(status);
            task.setTaskType(taskType);
            task.setRetries(retries);
            task.setUpdatedAt(LocalDateTime.now());
            log.info("Task Updated with Id : " + id);
            taskRepository.save(task);
            return TaskUpdated;
        }
        return null;

    }*/


}
