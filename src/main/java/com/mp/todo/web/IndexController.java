package com.mp.todo.web;

import com.mp.todo.domain.Role;
import com.mp.todo.domain.Task;
import com.mp.todo.domain.User;
import com.mp.todo.domain.exception.BadNameException;
import com.mp.todo.infrastructure.jpa.repository.RoleJpaRepository;
import com.mp.todo.infrastructure.jpa.repository.TaskJpaRepository;
import com.mp.todo.infrastructure.jpa.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    TaskJpaRepository taskJpaRepository;

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    RoleJpaRepository roleJpaRepository;

    @GetMapping("/init")
    public ResponseEntity<String> init() throws BadNameException {
        String output = "";

        taskJpaRepository.deleteAllInBatch();
        userJpaRepository.deleteAllInBatch();
        roleJpaRepository.deleteAllInBatch();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleJpaRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleJpaRepository.save(userRole);

        User user1 = new User();
        user1.setName("user1");
        user1.setPassword(encoder.encode("u1"));
        user1.addRole(userRole);
        userJpaRepository.save(user1);


        User user2 = new User();
        user2.setName("user2");
        user2.setPassword(encoder.encode("u2"));
        user2.addRole(userRole);
        userJpaRepository.save(user2);

        User admin = new User();
        admin.setName("admin");
        admin.setPassword(encoder.encode("a"));
        admin.addRole(userRole);
        admin.addRole(adminRole);
        userJpaRepository.save(admin);

        Task task1 = new Task();
        task1.setName("T1");
        task1.setUser(user1);
        taskJpaRepository.save(task1);

        Task task2 = new Task();
        task2.setName("T2");
        task2.setUser(user2);
        taskJpaRepository.save(task2);


        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
