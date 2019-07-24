package com.mp.todo.domain.repository;

import com.mp.todo.domain.Task;

public interface TaskRepository {
    public Task[] findAll();
    public void save(Task task);
}
