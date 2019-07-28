package com.mp.todo.domain;

import com.mp.todo.domain.exception.BadNameException;

import java.util.UUID;

public interface Task {
    String getId();
    String getName();
    void setName(String newName) throws BadNameException;
}
