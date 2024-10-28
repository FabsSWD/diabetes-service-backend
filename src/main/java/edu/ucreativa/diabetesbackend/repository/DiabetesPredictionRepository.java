package edu.ucreativa.diabetesbackend.repository;

import edu.ucreativa.diabetesbackend.model.DiabetesPrediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiabetesPredictionRepository extends JpaRepository<DiabetesPrediction, Long> {
}
