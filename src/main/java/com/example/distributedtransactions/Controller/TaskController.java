package com.example.distributedtransactions.Controller;

import com.example.distributedtransactions.Entity.TaskEntity;
import com.example.distributedtransactions.Helpers.DeleteTaskHelper;
import com.example.distributedtransactions.Helpers.RetrieveTaskHelper;
import com.example.distributedtransactions.Helpers.UpdateTaskHelper;
import com.example.distributedtransactions.Models.CreateTaskRequest;
import com.example.distributedtransactions.Repository.TaskRepository;
import com.example.distributedtransactions.Service.WorkerLogicService;
import com.example.distributedtransactions.Utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.distributedtransactions.Utils.CommonConstants.Deleted;

@RestController
@RequestMapping("/v1")
public class TaskController {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private RetrieveTaskHelper retrieveTaskHelper;
    @Autowired
    private DeleteTaskHelper deleteTaskHelper;
    @Autowired
    private WorkerLogicService workerLogicService;
    @Autowired
    private UpdateTaskHelper updateTaskHelper;
    @Autowired
    TaskRepository taskRepository;

    @PostMapping("/payload/{payload}/id/{id}/createTask")
    public String createTask(
            @PathVariable String payload,
            @PathVariable Long id,
            @RequestBody CreateTaskRequest createTaskRequest) {
        Utils utils = new Utils();
        utils.checkName(payload);
        if (!utils.containsNumber(payload)) {
            workerLogicService.workerLogic(payload, id, createTaskRequest.getStatus(),
                    createTaskRequest.getTaskType(), createTaskRequest.getRetries());
        }
        return "Task Created with id : " + id.toString();

    }

    @GetMapping("id/{id}/retrieveTask")
    public TaskEntity getTask(
            @PathVariable Long id) {
        log.info("Retreiving Task from Database");
        TaskEntity task = retrieveTaskHelper.getTask(id);
        return task;
    }

    @GetMapping("id/{id}/retrieveTaskName")
    public String getTaskName(
            @PathVariable Long id) {
        log.info("Retreiving Task from Database");
        TaskEntity task = taskRepository.findById(id).orElse(null);
        String taskName = task.getPayload().toString();
        return taskName;
    }

    @PutMapping("/payload/{payload}/id/{id}/updateTask")
    public String UpdateTask(
            @PathVariable String payload,
            @PathVariable Long id,
            @RequestBody CreateTaskRequest createTaskRequest) {

        log.info("Retreiving Task from Database");
        Utils utils = new Utils();
        utils.checkName(payload);
        String status="";
        if (!utils.containsNumber(payload)) {
            status= updateTaskHelper.updateTask(payload, id, createTaskRequest.getStatus(),
                    createTaskRequest.getTaskType(), createTaskRequest.getRetries());
        }
        return status;
    }


    @DeleteMapping("/id/{id}/deleteTask")
    public String deleteTask(
            @PathVariable Long id) {
        deleteTaskHelper.deleteTask(id);
        return Deleted;
    }
}
