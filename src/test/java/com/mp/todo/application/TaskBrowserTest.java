package com.mp.todo.application;

import com.mp.todo.domain.Task;
import com.mp.todo.domain.exception.BadNameException;
import com.mp.todo.domain.repository.TaskRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TaskBrowserTest {
    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    TaskBrowser taskBrowser;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getTaskList() throws BadNameException {
        when(taskRepository.findAll())
                .thenReturn(Arrays.asList(
                        new Task("t1"),
                        new Task("t2")
                ));


        List<Task> tasks = taskBrowser.getTaskList();
        assertEquals(tasks.size(), 2);
        assertEquals(tasks.get(0).getName(), "t1");
        assertEquals(tasks.get(1).getName(), "t2");
    }
}