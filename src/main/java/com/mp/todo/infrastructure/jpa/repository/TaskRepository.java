package com.mp.todo.infrastructure.jpa.repository;

import com.mp.todo.domain.Task;
import com.mp.todo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskRepository implements com.mp.todo.domain.repository.TaskRepository {
    @Autowired
    TaskJpaRepository taskJpaRepository;

    @Override
    public List<Task> findAll() {

        return taskJpaRepository.findAll();
    }

    @Override
    public Task find(String taskId) {
        return taskJpaRepository.findById(taskId).get();
    }

    @Override
    public Task findByUserAndId(User user, String taskId) {
        return taskJpaRepository.findByUserAndId(user, taskId);
    }

    @Override
    public List<Task> findAllByUser(User user) {
        return taskJpaRepository.findAllByUser(user);
    }

    @Override
    public void save(Task task) {
        taskJpaRepository.saveAndFlush(task);
    }

    @Override
    public void delete(Task task) {
        taskJpaRepository.delete(task);
    }
}
