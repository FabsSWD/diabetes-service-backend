package edu.ucreativa.diabetesbackend.service;

import edu.ucreativa.diabetesbackend.model.DiabetesPrediction;

public interface DiabetesPredictionService {
    DiabetesPrediction savePrediction(DiabetesPrediction prediction);
    boolean predictDiabetes(DiabetesPrediction prediction);
}
