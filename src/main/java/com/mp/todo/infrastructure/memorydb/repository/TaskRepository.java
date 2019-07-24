package com.mp.todo.infrastructure.memorydb.repository;

import com.mp.todo.domain.Task;
import com.mp.todo.domain.exception.InvalidTaskNameException;
import com.mp.todo.domain.factory.TaskFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskRepository implements com.mp.todo.domain.repository.TaskRepository {
    private List<Task> tasks;

    public TaskRepository() throws InvalidTaskNameException {
        this.tasks = new ArrayList<>();

        Task task1 = TaskFactory.buildTask("t1");
        this.tasks.add(task1);
    }

    @Override
    public Task[] findAll() {
        return this.tasks.toArray(Task[]::new);
    }

    @Override
    public void save(Task task) {
        this.tasks.add(task);
    }
}
