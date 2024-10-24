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

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private MLClient mlClient = new MLClient();  // Instancia del cliente de ML

    // Endpoint para realizar la predicción
    @PostMapping("/predict")
    public ResponseEntity<String> getPrediction(@RequestBody Map<String, Object> input) {
        try {
            // Convertir el input a JSON
            String inputJson = new ObjectMapper().writeValueAsString(input);

            // Invocar el modelo a través del cliente
            String prediction = mlClient.predict(inputJson);

            // Retornar la respuesta de la predicción
            return ResponseEntity.ok(prediction);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error al procesar la predicción");
        }
    }
}
