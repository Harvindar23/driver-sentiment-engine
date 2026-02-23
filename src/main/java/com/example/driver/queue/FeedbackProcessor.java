package com.example.driver.queue;

import com.example.driver.model.Feedback;
import com.example.driver.service.FeedbackService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
public class FeedbackProcessor {

    private final BlockingQueue<Feedback> feedbackQueue;
    private final FeedbackService feedbackService;

    public FeedbackProcessor(BlockingQueue<Feedback> feedbackQueue,
                             FeedbackService feedbackService) {
        this.feedbackQueue = feedbackQueue;
        this.feedbackService = feedbackService;
    }

    @PostConstruct
    public void startProcessing() {

        Thread worker = new Thread(() -> {
            while (true) {
                try {
                    Feedback feedback = feedbackQueue.take();

                    // Now this saves everything to DB
                    feedbackService.processFeedback(feedback);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        worker.setDaemon(true);
        worker.start();
    }
}