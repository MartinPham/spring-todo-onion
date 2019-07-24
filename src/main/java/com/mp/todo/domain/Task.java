package com.mp.todo.domain;

import com.mp.todo.domain.exception.InvalidTaskNameException;

public interface Task {
    public String getName() throws InvalidTaskNameException;
}
