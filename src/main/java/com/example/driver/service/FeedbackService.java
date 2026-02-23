package com.example.driver.service;

import com.example.driver.model.Alert;
import com.example.driver.model.Driver;
import com.example.driver.model.Feedback;
import com.example.driver.repository.AlertRepository;
import com.example.driver.repository.FeedbackRepository;
import com.example.driver.repository.DriverRepository;
import com.example.driver.strategy.SentimentStrategy;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    private final SentimentStrategy sentimentStrategy;
    private final DriverRepository driverRepository;
    private final FeedbackRepository feedbackRepository;
    private final AlertRepository alertRepository;
    private final AlertService alertService;

    public FeedbackService(SentimentStrategy sentimentStrategy,
                           DriverRepository driverRepository,
                           FeedbackRepository feedbackRepository,
                           AlertRepository alertRepository,
                           AlertService alertService) {
        this.sentimentStrategy = sentimentStrategy;
        this.driverRepository = driverRepository;
        this.feedbackRepository = feedbackRepository;
        this.alertRepository = alertRepository;
        this.alertService = alertService;
    }

    public Alert processFeedback(Feedback feedback) {

        // 1️⃣ Save feedback first
        feedbackRepository.save(feedback);

        // 2️⃣ Analyze sentiment
        double score = sentimentStrategy.analyze(feedback.getText());

        // 3️⃣ Fetch existing driver OR create new one
        Driver driver = driverRepository
                .findById(feedback.getDriverId())
                .orElse(new Driver(feedback.getDriverId()));

        // 4️⃣ Update driver stats
        driver.update(score);

        // 5️⃣ Save updated driver
        driverRepository.save(driver);

        // 6️⃣ Check for alert
        Alert alert = alertService.checkAndGenerateAlert(
                driver.getDriverId(),
                driver.getAverageScore()
        );

        // 7️⃣ Save alert if generated
        if (alert != null) {
            alertRepository.save(alert);
        }

        return alert;
    }
}