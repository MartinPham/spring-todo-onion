package com.mp.todo.domain;

import com.mp.todo.domain.exception.BadNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void construct() {
        assertThrows(BadNameException.class, () -> {
            Task task1 = new Task("");
        });

    }

    @Test
    void setName() throws BadNameException {
        Task task = new Task("t1");
        assertEquals(task.getName(), "t1");

    }
}