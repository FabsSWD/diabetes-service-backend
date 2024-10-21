package edu.ucreativa.diabetesbackend.repository;

import edu.ucreativa.diabetesbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}