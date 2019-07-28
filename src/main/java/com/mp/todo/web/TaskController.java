package com.mp.todo.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaskController {
    @GetMapping("/task")
    public String index(Model model, @RequestParam("name") String name)
    {
        model.addAttribute("name", name);
        return "web/task/index";
    }
}
