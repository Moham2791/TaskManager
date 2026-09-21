package com.example.distributedtransactions.Controller;

import com.example.distributedtransactions.Entity.TaskEntity;
import com.example.distributedtransactions.Helpers.CreateTaskService;
import com.example.distributedtransactions.Helpers.RetrieveTaskService;
import com.example.distributedtransactions.Helpers.UpdateTaskService;
import com.example.distributedtransactions.Utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;

@RestController("/v1")
public class controller {
    private static final Logger log = LoggerFactory.getLogger(controller.class);
    UpdateTaskService updateTaskService;
    RetrieveTaskService retrieveTaskService;
    CreateTaskService createTaskService;

    @PostMapping("/payload/{payload}/Id/{Id}/createTask")
    public String createTask(
            @RequestHeader ServerRequest.Headers header,
            @PathVariable String payload,
            @PathVariable Long id,
            @RequestBody String status,
            @RequestBody String taskType,
            @RequestBody Integer retries) {
        Utils utils = new Utils();
        utils.checkName(payload);
        if (!utils.containsNumber(payload)) {
            createTaskService.createTask(payload, id, status, taskType, retries);
        }
        return "Task Created with Id : " + id.toString();

    }

    @GetMapping("/payload/{payload}/Id/{Id}/retrieveTask")
    public TaskEntity getTask(
            @RequestHeader ServerRequest.Headers header,
            @PathVariable Long id) {

        log.info("Retreiving Task from Database");
        TaskEntity task = retrieveTaskService.getTask(id);
        return task;
    }

    @PutMapping("/payload/{payload}/Id/{Id}/updateTask")
    public TaskEntity updateTask(
            @RequestHeader ServerRequest.Headers header,
            @PathVariable String payload,
            @PathVariable Long id,
            @RequestBody String status,
            @RequestBody String taskType,
            @RequestBody Integer retries) {

        log.info("Retreiving Task from Database");
        TaskEntity task = retrieveTaskService.getTask(id);


        Utils utils = new Utils();
        utils.checkName(payload);
        if (!utils.containsNumber(payload)) {
            updateTaskService.updateTask(payload, id, status, taskType, retries);
        }
        return task;
    }


    @DeleteMapping("/Id/{Id}/deleteTask")
    public TaskEntity deleteTask(
            @RequestHeader ServerRequest.Headers header,
            @PathVariable String payload,
            @PathVariable Long id,
            @RequestBody String status,
            @RequestBody String taskType,
            @RequestBody Integer retries) {

        log.info("Retreiving Task from Database");
        TaskEntity task = retrieveTaskService.getTask(id);

        Utils utils = new Utils();
        utils.checkName(payload);
        if (!utils.containsNumber(payload)) {
            updateTaskService.updateTask(payload, id, status, taskType, retries);
        }
        return task;
    }
}
