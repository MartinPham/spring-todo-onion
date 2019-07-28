package com.mp.todo.web;

import com.mp.todo.application.TaskBrowser;
import com.mp.todo.application.TaskEditor;
import com.mp.todo.domain.Task;
import com.mp.todo.domain.exception.BadNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    TaskBrowser taskBrowser;

    @Autowired
    TaskEditor taskEditor;

    @GetMapping("/task")
    public String index(Model model, @RequestParam("name") String name) throws BadNameException {
        taskEditor.createNewTask("T1");
        taskEditor.createNewTask("T2");

        List<Task> tasks = taskBrowser.getTaskList();

        model.addAttribute("name", tasks.get(1).getName());
        return "web/task/index";
    }
}
