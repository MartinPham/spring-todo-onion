package com.mp.todo.backoffice;

import com.mp.todo.application.TaskBrowser;
import com.mp.todo.application.TaskEditor;
import com.mp.todo.domain.Task;
import com.mp.todo.domain.User;
import com.mp.todo.domain.exception.BadNameException;
import com.mp.todo.infrastructure.jpa.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller("backoffice.task")
public class TaskController {
    @Autowired
    TaskBrowser taskBrowser;

    @Autowired
    TaskEditor taskEditor;

    @Autowired
    UserJpaRepository userJpaRepository;

    @GetMapping("/backoffice/task")
    public String index(Model model) {
        List<Task> tasks = taskBrowser.getTaskList();

        model.addAttribute("tasks", tasks);
        return "backoffice/task/index";
    }


    @GetMapping("/backoffice/task/{id}/edit")
    public String edit(Model model, @PathVariable String id) {

        Task task = taskBrowser.getTaskDetail(id);

        model.addAttribute("task", task);
        return "backoffice/task/edit";
    }

    @PostMapping("/backoffice/task/{id}/edit")
    public String edit(@PathVariable String id, @RequestParam("name") String name) throws BadNameException {
        taskEditor.changeTaskName(id, name);
        return "redirect:/backoffice/task";
    }
}
