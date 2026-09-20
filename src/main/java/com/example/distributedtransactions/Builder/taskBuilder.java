package com.example.distributedtransactions.Builder;

import com.example.distributedtransactions.Utils.utils;
import org.springframework.beans.factory.annotation.Autowired;

public class taskBuilder {

    String taskName;
    String taskId;
    String taskType;
    @Autowired


    public String taskBuilder(String Name, String id, String type) {

        utils utils = new utils();
        // Validations
        utils.checkName(Name);
        utils.checkTaskType(type);
        utils.checkTaskId(id);


        return taskName;


    }
}
