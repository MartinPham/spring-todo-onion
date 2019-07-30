package com.mp.todo.infrastructure.jpa.repository;

import com.mp.todo.domain.Task;
import com.mp.todo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskJpaRepository extends JpaRepository<Task, String> {
    List<Task> findAllByUser(User user);
    Task findByUserAndId(User user, String id);
}
