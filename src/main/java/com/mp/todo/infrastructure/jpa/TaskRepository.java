package com.mp.todo.infrastructure.jpa;

import com.mp.todo.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskRepository implements com.mp.todo.domain.repository.TaskRepository {
    @Autowired
    TaskJpaRepository taskJpaRepository;

    @Override
    public List<Task> findAll() {
        List<Task> tasks = new ArrayList<>();
        taskJpaRepository.findAll().forEach((com.mp.todo.domain.entity.Task task) -> {
            tasks.add(task);
        });

        return tasks;
    }

    @Override
    public Task find(String taskId) {
        Optional<com.mp.todo.domain.entity.Task> task = taskJpaRepository.findById(taskId);
        return task.get();
    }

    @Override
    public void save(Task task) {
        taskJpaRepository.saveAndFlush((com.mp.todo.domain.entity.Task) task);
    }

    @Override
    public void delete(Task task) {
        taskJpaRepository.delete((com.mp.todo.domain.entity.Task) task);
    }
}
