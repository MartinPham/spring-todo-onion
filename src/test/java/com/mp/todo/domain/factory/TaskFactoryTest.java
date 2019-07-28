package com.mp.todo.domain.factory;

import com.mp.todo.domain.Task;
import com.mp.todo.domain.exception.BadNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskFactoryTest {
    @Test
    void construct() {
        assertThrows(BadNameException.class, () -> {
            Task task1 = TaskFactory.buildTaskWithName("");
        });

    }

    @Test
    void setName() throws BadNameException {
        Task task = TaskFactory.buildTaskWithName("t1");
        assertEquals(task.getName(), "t1");

    }
}