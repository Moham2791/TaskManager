package com.example.distributedtransactions.Service;

import com.example.distributedtransactions.Entity.TaskEntity;
import com.example.distributedtransactions.Helpers.DeleteTaskHelper;
import com.example.distributedtransactions.Helpers.RetrieveTaskHelper;
import com.example.distributedtransactions.Helpers.UpdateTaskHelper;
import com.example.distributedtransactions.Repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import static com.example.distributedtransactions.Utils.CommonConstants.Pending;

public class Polling {
    private static final Logger log = LoggerFactory.getLogger(Polling.class);
    @Autowired
    private RetrieveTaskHelper retrieveTaskHelper;
    @Autowired
    private DeleteTaskHelper deleteTaskHelper;
    @Autowired
    private WorkerLogicService workerLogicService;
    @Autowired
    private UpdateTaskHelper updateTaskHelper;
    @Autowired
    private TaskRepository taskRepository;

    @Autowired

    @Scheduled(fixedRate = 1000)
    public void polling(String payload, Long id, String status, String taskType, Integer retries) {

        TaskEntity task = retrieveTaskHelper.getTask(id);

        if (task != null && task.getStatus() != null && task.getStatus() == Pending) {
            try {
                updateTaskHelper.updatePickedTask(payload, id, status, taskType, retries);
            } catch (Exception e) {
                log.info("Exception in updateTaskHelper.updatePickedTask", e);
                log.warn("Continuing polling");
            }
        }


    }

}
