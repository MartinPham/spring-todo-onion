package com.mp.todo.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskController {
    @RequestMapping("/task")
    public String index()
    {
        return "web/task/index";
    }
}
