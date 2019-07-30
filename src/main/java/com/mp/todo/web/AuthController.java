package com.mp.todo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/auth/login")
    public String login() {
        return "web/auth/login";
    }
    @GetMapping("/auth/logout")
    public String logout() {
        return "web/auth/logout";
    }
}
