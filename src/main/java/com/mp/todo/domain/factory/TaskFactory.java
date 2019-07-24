package com.mp.todo.domain.factory;

import com.mp.todo.domain.Task;
import com.mp.todo.domain.exception.InvalidTaskNameException;

public class TaskFactory {
    public static Task buildTask(String name) throws InvalidTaskNameException {
        if(name.isEmpty())
        {
            throw new InvalidTaskNameException("Task name should not be empty");
        }

        Task task = new com.mp.todo.domain.entity.Task(name);
        return task;
    }
}
