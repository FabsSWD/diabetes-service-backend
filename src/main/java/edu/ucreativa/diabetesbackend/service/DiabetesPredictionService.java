package edu.ucreativa.diabetesbackend.service;

import edu.ucreativa.diabetesbackend.model.DiabetesPrediction;
import java.util.List;

public interface DiabetesPredictionService {
    DiabetesPrediction savePrediction(DiabetesPrediction prediction);
    boolean predictDiabetes(DiabetesPrediction prediction); // Firma del método predictDiabetes
    List<DiabetesPrediction> getPredictionsByUser(String username);
    List<DiabetesPrediction> getPublicPredictions();
}
