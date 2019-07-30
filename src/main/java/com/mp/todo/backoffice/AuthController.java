package com.mp.todo.backoffice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("backoffice.auth")
public class AuthController {
    @GetMapping("/backoffice/auth/login")
    public String login() {
        return "backoffice/auth/login";
    }

    @GetMapping("/backoffice/auth/logout")
    public String logout() {
        return "backoffice/auth/logout";
    }
}
