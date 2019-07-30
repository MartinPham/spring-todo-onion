package com.mp.todo.infrastructure.jpa.repository;

import com.mp.todo.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleJpaRepository extends JpaRepository<Role, String> {
}
