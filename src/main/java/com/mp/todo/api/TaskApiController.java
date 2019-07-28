package com.mp.todo.api;

import com.mp.todo.application.TaskBrowser;
import com.mp.todo.application.TaskEditor;
import com.mp.todo.domain.Task;
import com.mp.todo.domain.exception.BadNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskApiController {
    @Autowired
    TaskBrowser taskBrowser;

    @Autowired
    TaskEditor taskEditor;

    @GetMapping("/api/task")
    public List<Task> browse()
    {
        return taskBrowser.getTaskList();
    }

    @GetMapping("/api/task/add")
    public void add(@RequestParam("name") String name) throws BadNameException {
        taskEditor.createNewTask(name);
    }

    @GetMapping("/api/task/{id}/edit")
    public void edit(@PathVariable String id, @RequestParam("name") String name) throws BadNameException {
        taskEditor.changeTaskName(id, name);
    }
}
