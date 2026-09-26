package com.example.distributedtransactions.Service;

import com.example.distributedtransactions.Entity.TaskEntity;
import com.example.distributedtransactions.Helpers.RetrieveTaskHelper;
import com.example.distributedtransactions.Helpers.UpdateTaskHelper;
import com.example.distributedtransactions.Repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.example.distributedtransactions.Utils.CommonConstants.Pending;

@Service
public class Polling {
    private static final Logger log = LoggerFactory.getLogger(Polling.class);
    @Autowired
    private RetrieveTaskHelper retrieveTaskHelper;
    @Autowired
    private UpdateTaskHelper updateTaskHelper;
    @Autowired
    private TaskRepository taskRepository;

    @Scheduled(fixedRate = 1000)
    public void polling() {
        TaskEntity task = taskRepository.findFirstPendingTask();
        log.info("Polling fired");
        if (task != null && task.getStatus() != null && task.getStatus().equalsIgnoreCase(Pending)) {
            try {
                updateTaskHelper.updatePickedTask(task.getPayload(),
                        task.getId(), task.getStatus(), task.getTaskType(),
                        task.getRetries());
            } catch (Exception e) {
                log.info("Exception in updateTaskHelper.updatePickedTask", e);
                log.warn("Continuing polling");
            }
        }


    }

}
