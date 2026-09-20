package com.example.distributedtransactions.Builder;

import com.example.distributedtransactions.Utils.Utils;

public class TaskBuilder {

    String taskName;
    String taskId;
    String taskType;


    public String taskBuilder(String Name, String id, String type) {

        Utils utils = new Utils();
        utils.checkName(Name);
        utils.checkTaskType(type);
        utils.checkTaskId(id);
        return taskName;


    }
}
