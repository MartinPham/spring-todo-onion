package com.mp.todo;

import com.mp.todo.application.TaskBrowser;
import com.mp.todo.application.TaskCreator;
import com.mp.todo.domain.Task;
import com.mp.todo.domain.exception.InvalidTaskNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskApiController {
    @Autowired
    TaskBrowser taskBrowser;

    @Autowired
    TaskCreator taskCreator;

    public TaskApiController() {
    }

    @RequestMapping("/taskApi")
    public Task[] browse() {
        return this.taskBrowser.getAllTasks();
    }

    @RequestMapping(value = "/taskApi/add", method = RequestMethod.POST)
    public void add(String name) throws InvalidTaskNameException {
        taskCreator.createTask(name);
    }
}