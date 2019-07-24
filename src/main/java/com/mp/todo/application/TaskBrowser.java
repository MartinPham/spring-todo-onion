package com.mp.todo.application;

import com.mp.todo.domain.Task;
import com.mp.todo.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskBrowser {
    @Autowired
    TaskRepository taskRepository;

    public Task[] getAllTasks()
    {
        return this.taskRepository.findAll();
    }
}
