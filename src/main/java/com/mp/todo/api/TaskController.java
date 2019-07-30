package com.mp.todo.api;

import com.mp.todo.application.TaskBrowser;
import com.mp.todo.application.TaskEditor;
import com.mp.todo.domain.Task;
import com.mp.todo.domain.User;
import com.mp.todo.domain.exception.BadNameException;
import com.mp.todo.infrastructure.jpa.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController("api.task")
public class TaskController {
    @Autowired
    TaskBrowser taskBrowser;

    @Autowired
    TaskEditor taskEditor;

    @Autowired
    UserJpaRepository userJpaRepository;

    @GetMapping("/api/task")
    public List<Task> browse(Principal principal)
    {
        User user = userJpaRepository.findByName(principal.getName());
        return taskBrowser.getTaskListByUser(user);
    }

    @GetMapping("/api/task/add")
    public void add(@RequestParam("name") String name) throws BadNameException {
//        taskEditor.createNewTask(name);
    }

    @GetMapping("/api/task/{id}/edit")
    public void edit(@PathVariable String id, @RequestParam("name") String name) throws BadNameException {
        taskEditor.changeTaskName(id, name);
    }
}
