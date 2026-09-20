package com.example.distributedtransactions.Builder;

import com.example.distributedtransactions.Utils.Utils;

public class TaskBuilder {

    String taskName;
    String taskId;
    String taskType;


    public void taskBuilder(String Name, String id,String type){

        Utils utils = new Utils();
        utils.checkName(Name);



    }
}
