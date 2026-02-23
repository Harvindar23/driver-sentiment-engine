package com.example.driver.service;

import com.example.driver.model.Alert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

    @Value("${alert.threshold}")
    private double threshold;

    public Alert checkAndGenerateAlert(String driverId, double averageScore) {

        if (averageScore >= threshold) {
            return null; // No alert if above threshold
        }

        // Directly generate alert if below threshold
        return new Alert(driverId, averageScore);
    }
}