package com.mp.todo.infrastructure.jpa.repository;

import com.mp.todo.domain.Task;
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
    public void save(Task task) {
        taskJpaRepository.saveAndFlush(task);
    }

    @Override
    public void delete(Task task) {
        taskJpaRepository.delete(task);
    }
}
