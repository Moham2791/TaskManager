package com.example.distributedtransactions;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class TaskManagerApplication {
    public static void main(String[] args) {

        System.out.println("Starting TaskManagerApplication");

    }
}
