package edu.ucreativa.diabetesbackend.controller;

import edu.ucreativa.diabetesbackend.service.UserService;
import edu.ucreativa.diabetesbackend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/exists")
    public boolean userExists(@RequestParam String username) {
        return userService.userExists(username);
    }

    @PutMapping("/update-password")
    public String updatePassword(@RequestParam String username, @RequestParam String newPassword) {
        boolean updated = userService.updateUserPassword(username, newPassword);
        return updated ? "Password updated successfully" : "User not found";
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam String username, @RequestParam String password) {
        boolean deleted = userService.deleteUser(username, password);
        return deleted ? "User deleted successfully" : "Invalid username or password";
    }
}
