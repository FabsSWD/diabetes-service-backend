package edu.ucreativa.diabetesbackend.controller;

import edu.ucreativa.diabetesbackend.model.DiabetesPrediction;
import edu.ucreativa.diabetesbackend.service.DiabetesPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prediction")
@CrossOrigin(origins = "http://localhost:3000")
public class DiabetesPredictionController {

    @Autowired
    private DiabetesPredictionService predictionService;

    @PostMapping("/add")
    public ResponseEntity<DiabetesPrediction> addPrediction(@RequestBody DiabetesPrediction prediction) {
        // Llamar a la API de predicción y actualizar el resultado en el objeto de predicción
        boolean hasDiabetes = predictionService.predictDiabetes(prediction);
        prediction.setDiabetes(hasDiabetes);

        // Guardar la predicción en la base de datos
        DiabetesPrediction savedPrediction = predictionService.savePrediction(prediction);
        return ResponseEntity.ok(savedPrediction);
    }
}
