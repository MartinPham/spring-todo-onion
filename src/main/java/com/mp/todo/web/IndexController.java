package com.mp.todo.web;

import com.mp.todo.application.TaskBrowser;
import com.mp.todo.application.TaskEditor;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/")
    public ResponseEntity<String> index() throws BadNameException {
        String output = "";

        taskJpaRepository.deleteAllInBatch();
        userJpaRepository.deleteAllInBatch();
        roleJpaRepository.deleteAllInBatch();

        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleJpaRepository.save(adminRole);

        Role userRole = new Role();
        userRole.setName("USER");
        roleJpaRepository.save(userRole);

        User user = new User();
        user.setName("user");
        user.setPassword("u");
        user.addRole(userRole);
        userJpaRepository.save(user);

        User admin = new User();
        admin.setName("admin");
        admin.setPassword("a");
        admin.addRole(userRole);
        admin.addRole(adminRole);
        userJpaRepository.save(admin);

        Task task1 = new Task();
        task1.setName("T1");
        task1.setUser(user);
        taskJpaRepository.save(task1);


        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
