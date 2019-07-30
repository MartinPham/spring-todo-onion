package com.mp.todo.application;

import com.mp.todo.domain.Task;
import com.mp.todo.domain.User;
import com.mp.todo.domain.exception.BadNameException;
import com.mp.todo.domain.factory.TaskFactory;
import com.mp.todo.domain.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskEditor {
    @Autowired
    TaskRepository taskRepository;

    public void createNewTask(User user, String taskName) throws BadNameException {
        Task task = TaskFactory.buildTaskWithName(taskName);
        task.setUser(user);
        taskRepository.save(task);
    }

    public void changeTaskName(String taskId, String newName) throws BadNameException {
        Task task = taskRepository.find(taskId);
        task.setName(newName);
        taskRepository.save(task);
    }

    public void changeTaskNameByUser(User user, String taskId, String newName) throws BadNameException {
        Task task = taskRepository.findByUserAndId(user, taskId);
        task.setName(newName);
        taskRepository.save(task);
    }

}
