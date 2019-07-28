package com.mp.todo.domain.entity;


import com.mp.todo.domain.exception.BadNameException;

import java.util.UUID;

public class Task implements com.mp.todo.domain.Task {
    UUID id;
    String name;

    public Task(String name) throws BadNameException {
        id = UUID.randomUUID();
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) throws BadNameException {
        if (newName.isEmpty())
        {
            throw new BadNameException("Task name should not be empty");
        }
        name = newName;
    }
}