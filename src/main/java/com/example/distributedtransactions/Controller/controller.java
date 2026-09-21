package com.example.distributedtransactions.Controller;

import com.example.distributedtransactions.Utils.Utils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.ServerRequest;

@RestController("/v1")
public class controller {

    @PostMapping("/payload/{payload}/Id/{Id}/createTask")
    public String createTask(
            @RequestHeader ServerRequest.Headers header,
            @PathVariable String payload,
            @PathVariable Long id) {
        Utils utils = new Utils();
        utils.checkName(payload);
        if (!utils.containsNumber(payload)) {

        }
        return "Task Created with Id : " + id.toString();

    }
}
