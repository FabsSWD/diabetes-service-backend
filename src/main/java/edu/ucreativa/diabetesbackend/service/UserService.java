package edu.ucreativa.diabetesbackend.service;

import edu.ucreativa.diabetesbackend.model.User;

public interface UserService {
    User saveUser(User user);
    boolean validateUserPassword(String username, String rawPassword);
}