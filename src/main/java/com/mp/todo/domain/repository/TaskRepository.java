package com.mp.todo.domain.repository;

import com.mp.todo.domain.Task;
import com.mp.todo.domain.User;

import java.util.List;

public interface TaskRepository {
    List<Task> findAll();
    Task find(String taskId);
    Task findByUserAndId(User user, String taskId);
    List<Task> findAllByUser(User user);
    void save(Task task);
    void delete(Task task);
}
