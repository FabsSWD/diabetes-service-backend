package edu.ucreativa.diabetesbackend.service;

import edu.ucreativa.diabetesbackend.model.User;

public interface UserService {
    User saveUser(User user);
    boolean validateUserPassword(String username, String rawPassword);
    boolean userExists(String username);
    boolean updatePassword(String username, String newPassword);
    boolean deleteUser(String username, String rawPassword);
}