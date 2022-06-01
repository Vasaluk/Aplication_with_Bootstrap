package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;

@Controller
public class UsersController {
    @Autowired
    private UserServiceImp userService;

    @GetMapping("/user")
    public String printUsers(ModelMap model, Principal principal) {
        model.addAttribute("messages", userService.loadUserByUsername(principal.getName()));
        return "user/user";
    }
}
