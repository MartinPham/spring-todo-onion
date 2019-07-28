package com.mp.todo.domain;

import com.mp.todo.domain.exception.BadNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void setName() {
        assertThrows(BadNameException.class, () -> {
            Task task = new Task("");
        });

    }
}