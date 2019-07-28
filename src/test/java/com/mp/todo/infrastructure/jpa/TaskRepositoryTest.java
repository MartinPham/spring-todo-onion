package com.mp.todo.infrastructure.jpa;

import com.mp.todo.domain.Task;
import com.mp.todo.domain.exception.BadNameException;
import com.mp.todo.domain.factory.TaskFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TaskRepositoryTest {
    @Mock
    TaskJpaRepository taskJpaRepository;

    @InjectMocks
    TaskRepository taskRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() throws BadNameException {
        when(taskJpaRepository.findAll())
                .thenReturn(Arrays.asList(
                        TaskFactory.buildTaskWithName("t1"),
                        TaskFactory.buildTaskWithName("t2")
                ));

        List<Task> tasks = taskRepository.findAll();

        assertEquals(tasks.size(), 2);
        assertEquals(tasks.get(0).getName(), "t1");
        assertEquals(tasks.get(1).getName(), "t2");
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }
}