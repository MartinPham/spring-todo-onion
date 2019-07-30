package com.mp.todo.application;

import com.mp.todo.domain.Task;
import com.mp.todo.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskBrowser {
    @Autowired
    TaskRepository taskRepository;

    public List<Task> getTaskList() {
        return taskRepository.findAll();
    }
}