package edu.ucreativa.diabetesbackend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String postedBy;

    private int pregnancies;
    private int glucose;
    private int bloodPressure;
    private int skinThickness;
    private int insulin;
    private double bmi;
    private double diabetesPedigreeFunction;
    private int age;

    @Column(nullable = false)
    private boolean diabetes;

    @Column(name = "is_public", nullable = false)
    @JsonProperty("isPublic")
    private boolean isPublic; // Asegúrate de que el nombre de la columna esté especificado aquí
}
