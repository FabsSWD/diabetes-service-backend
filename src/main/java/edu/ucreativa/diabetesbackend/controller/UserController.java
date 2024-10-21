package edu.ucreativa.diabetesbackend.controller;

import edu.ucreativa.diabetesbackend.service.UserService;
import edu.ucreativa.diabetesbackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody User user) {
        userService.saveUser(user);
        return "New user created";
    }

    @PostMapping("/validate")
    public boolean validate(@RequestParam String username, @RequestParam String password) {
        return userService.validateUserPassword(username, password);
    }
}
