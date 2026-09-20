package com.example.distributedtransactions;

import org.springframework.scheduling.config.Task;

public class TaskBuilder {

    String taskName;
    String taskId;
    String taskType;


    public void taskBuilder(String Name, String id,String type){

        Utils utils = new Utils();
        utils.checkName(Name);



    }
}
