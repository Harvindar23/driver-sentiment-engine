package com.example.driver.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    private String driverId;

    private double totalScore;
    private int feedbackCount;
    private double averageScore;

    private LocalDateTime lastUpdated;

    // Required by JPA
    public Driver() {
    }

    public Driver(String driverId) {
        this.driverId = driverId;
        this.totalScore = 0.0;
        this.feedbackCount = 0;
        this.averageScore = 0.0;
        this.lastUpdated = LocalDateTime.now();
    }

    public void update(double score) {
        this.totalScore += score;
        this.feedbackCount++;
        this.averageScore = totalScore / feedbackCount;
        this.lastUpdated = LocalDateTime.now();
    }

    public String getDriverId() { return driverId; }
    public double getTotalScore() { return totalScore; }
    public int getFeedbackCount() { return feedbackCount; }
    public double getAverageScore() { return averageScore; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
}