package com.example.distributedtransactions.Helpers;

import com.example.distributedtransactions.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class DeleteTaskService {

    @Autowired
    TaskRepository taskRepository;

    public String deleteTask(Long id) {
        System.out.println("deleteTask");
        taskRepository.deleteById(id);
       return "The task has been deleted";
    }


}
