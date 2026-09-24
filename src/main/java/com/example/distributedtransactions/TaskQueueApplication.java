package com.example.distributedtransactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootConfiguration
@SpringBootApplication
public class TaskQueueApplication {
    public static void main(String[] args) {

        SpringApplication.run(TaskQueueApplication.class, args);


    }
}
