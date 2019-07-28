package com.mp.todo.domain;

import com.mp.todo.domain.exception.BadNameException;

import java.util.UUID;

public class Task {
    UUID id;
    String name;

    public Task(String name) throws BadNameException {
        this.id = UUID.randomUUID();
        this.setName(name);
    }

    public void setName(String name) throws BadNameException {
        if (name.isEmpty())
        {
            throw new BadNameException("Task name should not be empty");
        }
        this.name = name;
    }
}
