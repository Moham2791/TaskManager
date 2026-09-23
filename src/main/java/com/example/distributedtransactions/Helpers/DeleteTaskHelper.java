package com.example.distributedtransactions.Helpers;

import com.example.distributedtransactions.Repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class DeleteTaskHelper {

    private static final Logger log = LoggerFactory.getLogger(DeleteTaskHelper.class);
    @Autowired
    TaskRepository taskRepository;

    public String deleteTask(Long id) {
        log.info("Deleting Task with Id : " + id);
        taskRepository.deleteById(id);
       return "The task has been deleted";
    }


}
