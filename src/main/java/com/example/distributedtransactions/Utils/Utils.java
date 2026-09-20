package com.example.distributedtransactions.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {


    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    public String checkName(String name) {

        try {
            if (!(name.length() > 255) && !containsNumber(name)) {

                return name;
            }
            log.info("Either name length exceeds allowed length 255 chars or Name contains a character other than alphabets");
            return "invalid name format";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean containsNumber(String input) {
        if (input == null) {
            return false;
        }
        return input.chars().anyMatch(Character::isDigit);
    }

    public String checkTaskId(String taskId) {
        try {
            if (taskId != null && !taskId.isEmpty() && taskId.length() <= 100 && isNumeric(taskId)) {
                return taskId;
            }
            log.info("taskId is invalid: must be non-empty, max 100 chars, and contain only numbers");
            return "invalid taskId format";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String checkTaskType(String taskType) {
        try {
            if (taskType != null && !taskType.isEmpty() && taskType.length() <= 50 && !containsNumber(taskType)) {
                return taskType;
            }
            log.info("taskType is invalid: must be non-empty, max 50 chars, and contain only alphabets");
            return "invalid taskType format";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isNumeric(String input) {
        if (input == null) {
            return false;
        }
        return input.chars().allMatch(Character::isDigit);
    }

}
