package com.mp.todo.domain.repository;

import com.mp.todo.domain.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> findAll();
    Task find(String taskId);
    void save(Task task);
    void delete(Task task);
}
