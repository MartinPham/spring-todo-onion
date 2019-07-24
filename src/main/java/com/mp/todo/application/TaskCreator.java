package com.mp.todo.application;

import com.mp.todo.domain.Task;
import com.mp.todo.domain.exception.InvalidTaskNameException;
import com.mp.todo.domain.factory.TaskFactory;
import com.mp.todo.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskCreator {
    @Autowired
    TaskRepository taskRepository;

    public void createTask(String name) throws InvalidTaskNameException {
        Task task = TaskFactory.buildTask(name);
        taskRepository.save(task);
    }
}
