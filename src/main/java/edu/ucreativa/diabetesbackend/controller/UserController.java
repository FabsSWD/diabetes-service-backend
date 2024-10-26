package edu.ucreativa.diabetesbackend.controller;

import edu.ucreativa.diabetesbackend.service.UserService;
import edu.ucreativa.diabetesbackend.model.User;
import edu.ucreativa.diabetesbackend.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public boolean validate(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        return userService.validateUserPassword(username, password);
    }

    @GetMapping("/exists")
    public boolean userExists(@RequestParam String username) {
        return userService.userExists(username);
    }

    @PutMapping("/update-password")
    public ResponseEntity<Boolean> updatePassword(@RequestParam String username, @RequestParam String newPassword) {
        boolean isUpdated = userService.updatePassword(username, newPassword);
        return ResponseEntity.ok(isUpdated);
    }

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam String username, @RequestParam String password) {
        boolean deleted = userService.deleteUser(username, password);
        return deleted ? "User deleted successfully" : "Invalid username or password";
    }
}
