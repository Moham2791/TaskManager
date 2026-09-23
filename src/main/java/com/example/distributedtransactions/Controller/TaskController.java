package com.example.distributedtransactions.Controller;

import com.example.distributedtransactions.Entity.TaskEntity;
import com.example.distributedtransactions.Helpers.CreateTaskHelper;
import com.example.distributedtransactions.Helpers.DeleteTaskHelper;
import com.example.distributedtransactions.Helpers.RetrieveTaskHelper;
import com.example.distributedtransactions.Helpers.UpdateTaskHelper;
import com.example.distributedtransactions.Utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;

import static com.example.distributedtransactions.Utils.CommonConstants.Deleted;

@RestController
@RequestMapping("/v1")
public class TaskController {
    private static final Logger log = LoggerFactory.getLogger(TaskController.class);
    @Autowired
    private UpdateTaskHelper updateTaskHelper;
    @Autowired
    private RetrieveTaskHelper retrieveTaskHelper;
    @Autowired
    private CreateTaskHelper createTaskHelper;
    @Autowired
    private DeleteTaskHelper deleteTaskHelper;

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
            createTaskHelper.createTask(payload, id, status, taskType, retries);
        }
        return "Task Created with Id : " + id.toString();

    }

    @GetMapping("/payload/{payload}/Id/{Id}/retrieveTask")
    public TaskEntity getTask(
            @RequestHeader ServerRequest.Headers header,
            @PathVariable Long id) {

        log.info("Retreiving Task from Database");
        TaskEntity task = retrieveTaskHelper.getTask(id);
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
        TaskEntity task = retrieveTaskHelper.getTask(id);


        Utils utils = new Utils();
        utils.checkName(payload);
        if (!utils.containsNumber(payload)) {
            updateTaskHelper.updateTask(payload, id, status, taskType, retries);
        }
        return task;
    }


    @DeleteMapping("/Id/{Id}/deleteTask")
    public String deleteTask(
            @RequestHeader ServerRequest.Headers header,
            @PathVariable String payload,
            @PathVariable Long id,
            @RequestBody String status,
            @RequestBody String taskType,
            @RequestBody Integer retries) {

        log.info("Retreiving Task from Database");
        TaskEntity task = retrieveTaskHelper.getTask(id);

        Utils utils = new Utils();
        utils.checkName(payload);
        if (!utils.containsNumber(payload)) {
            deleteTaskHelper.deleteTask(id);
        }
        return Deleted;
    }
}
