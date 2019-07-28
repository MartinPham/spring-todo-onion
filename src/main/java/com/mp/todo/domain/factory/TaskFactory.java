package com.mp.todo.domain.factory;

import com.mp.todo.domain.entity.Task;
import com.mp.todo.domain.exception.BadNameException;

public class TaskFactory {
    public static Task buildTaskWithName(String name) throws BadNameException {
        Task task = new Task();
        task.setName(name);
        return task;
    }
}
