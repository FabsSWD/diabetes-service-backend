package edu.ucreativa.diabetesbackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ucreativa.diabetesbackend.model.DiabetesPrediction;
import edu.ucreativa.diabetesbackend.repository.DiabetesPredictionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiabetesPredictionServiceImpl implements DiabetesPredictionService {

    @Autowired
    private DiabetesPredictionRepository predictionRepository;

    private static final String PREDICTION_API_URL = "http://localhost:5000/predict";

    @Override
    public DiabetesPrediction savePrediction(DiabetesPrediction prediction) {
        return predictionRepository.save(prediction);
    }

    @Override
    public boolean predictDiabetes(DiabetesPrediction prediction) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        // Crear el mapa de datos para la solicitud
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("input_data", Map.of(
                "Pregnancies", prediction.getPregnancies(),
                "Glucose", prediction.getGlucose(),
                "BloodPressure", prediction.getBloodPressure(),
                "SkinThickness", prediction.getSkinThickness(),
                "Insulin", prediction.getInsulin(),
                "BMI", prediction.getBmi(),
                "DiabetesPedigreeFunction", prediction.getDiabetesPedigreeFunction(),
                "Age", prediction.getAge()
        ));

        try {
            // Llamar a la API de Python
            String response = restTemplate.postForObject(PREDICTION_API_URL, requestData, String.class);
            JsonNode responseJson = objectMapper.readTree(response);

            // Obtener el resultado de la predicción (1 = diabetes, 0 = no diabetes)
            boolean hasDiabetes = responseJson.get("prediction").asInt() == 1;
            prediction.setDiabetes(hasDiabetes);

            // Guardar la predicción en la base de datos
            savePrediction(prediction);
            return hasDiabetes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error calling prediction API", e);
        }
    }

    @Override
    public List<DiabetesPrediction> getPredictionsByUser(String username) {
        return predictionRepository.findAll()
                .stream()
                .filter(prediction -> prediction.getPostedBy().equals(username))
                .collect(Collectors.toList());
    }

    @Override
    public List<DiabetesPrediction> getPublicPredictions() {
        return predictionRepository.findAll()
                .stream()
                .filter(DiabetesPrediction::isPublic)
                .collect(Collectors.toList());
    }
}
