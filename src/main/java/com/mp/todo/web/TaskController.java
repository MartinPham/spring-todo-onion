package com.mp.todo.web;

import com.mp.todo.application.TaskBrowser;
import com.mp.todo.application.TaskEditor;
import com.mp.todo.domain.Task;
import com.mp.todo.domain.User;
import com.mp.todo.domain.exception.BadNameException;
import com.mp.todo.infrastructure.jpa.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class TaskController {
    @Autowired
    TaskBrowser taskBrowser;

    @Autowired
    TaskEditor taskEditor;

    @Autowired
    UserJpaRepository userJpaRepository;

    @GetMapping("/task")
    public String index(Model model, Principal principal) {
        User user = userJpaRepository.findByName(principal.getName());
        List<Task> tasks = taskBrowser.getTaskListByUser(user);

        model.addAttribute("tasks", tasks);
        return "web/task/index";
    }

    @PostMapping("/task/add")
    public String add(Principal principal, @RequestParam("name") String name) throws BadNameException {
        User user = userJpaRepository.findByName(principal.getName());
        List<Task> tasks = taskBrowser.getTaskListByUser(user);

        taskEditor.createNewTask(user, name);
        return "redirect:/task";
    }

    @GetMapping("/task/{id}/edit")
    public String edit(Model model, @PathVariable String id) {

        Task task = taskBrowser.getTaskDetail(id);

        model.addAttribute("task", task);
        return "web/task/edit";
    }

    @PostMapping("/task/{id}/edit")
    public String edit(@PathVariable String id, @RequestParam("name") String name) throws BadNameException {
        taskEditor.changeTaskName(id, name);
        return "redirect:/task";
    }
}
