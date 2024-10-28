package edu.ucreativa.diabetesbackend.controller;

import edu.ucreativa.diabetesbackend.model.DiabetesPrediction;
import edu.ucreativa.diabetesbackend.service.DiabetesPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

    // Endpoint para obtener todas las predicciones de un usuario
    @GetMapping("/user")
    public ResponseEntity<List<DiabetesPrediction>> getUserPredictions(@RequestParam String username) {
        List<DiabetesPrediction> predictions = predictionService.getPredictionsByUser(username);
        return ResponseEntity.ok(predictions);
    }

    // Endpoint para obtener todas las predicciones públicas
    @GetMapping("/public")
    public ResponseEntity<List<DiabetesPrediction>> getPublicPredictions() {
        List<DiabetesPrediction> publicPredictions = predictionService.getPublicPredictions();
        return ResponseEntity.ok(publicPredictions);
    }
}
