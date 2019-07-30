package com.mp.todo.infrastructure.jpa;

import com.mp.todo.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TaskJpaRepository extends JpaRepository<Task, String> {
}
