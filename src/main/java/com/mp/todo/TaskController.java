package com.mp.todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TaskController {
    @RequestMapping("/task")
    public ResponseEntity<String> browse()
    {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping("/task/add")
    public String add()
    {
        return "add";
    }
}


