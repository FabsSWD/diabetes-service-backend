package edu.ucreativa.diabetesbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "diabetes_prediction")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DiabetesPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String postedBy; // Nombre del usuario que publicó la predicción

    private int pregnancies;
    private int glucose;
    private int bloodPressure;
    private int skinThickness;
    private int insulin;
    private double bmi;
    private double diabetesPedigreeFunction;
    private int age;

    @Column(nullable = false)
    private boolean diabetes; // Resultado de la predicción (true para diabetes, false para no diabetes)

    @Column(nullable = false)
    private boolean isPublic; // Indica si la predicción es pública o privada
}
