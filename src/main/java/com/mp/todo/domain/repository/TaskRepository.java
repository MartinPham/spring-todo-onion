package com.mp.todo.domain.repository;

import com.mp.todo.domain.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> findAll();
    void save(Task task);
    void remove(Task task);
}
