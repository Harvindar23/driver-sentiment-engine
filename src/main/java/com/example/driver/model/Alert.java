package com.example.driver.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String driverId;
    private double averageScore;
    private LocalDateTime alertTime;

    // Required by JPA
    public Alert() {
    }

    public Alert(String driverId, double averageScore) {
        this.driverId = driverId;
        this.averageScore = averageScore;
        this.alertTime = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getDriverId() { return driverId; }
    public double getAverageScore() { return averageScore; }
    public LocalDateTime getAlertTime() { return alertTime; }
}