package com.mp.todo.infrastructure.jpa.repository;

import com.mp.todo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, String> {
    User findByName(String name);
}
