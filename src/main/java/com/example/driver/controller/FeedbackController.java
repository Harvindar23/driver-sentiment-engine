package com.example.driver.controller;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.driver.model.Feedback;

import java.util.concurrent.BlockingQueue;

@Controller
public class FeedbackController {

    private final BlockingQueue<Feedback> feedbackQueue;

    public FeedbackController(BlockingQueue<Feedback> feedbackQueue) {
        this.feedbackQueue = feedbackQueue;
    }

    // Show form page
    @GetMapping("/feedback-form")
    public String showFeedbackForm() {
        return "feedback";
    }

    //Handle HTML form submission
    @PostMapping("/feedback")
    public String submitFeedback(
            @RequestParam String driverId,
            @RequestParam String employeeId,
            @RequestParam String text,
            RedirectAttributes redirectAttributes
    ) throws InterruptedException {

        Feedback feedback = new Feedback(driverId, employeeId, text);
        feedbackQueue.put(feedback);

        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Feedback submitted successfully!"
        );

        return "redirect:/feedback-form";
    }
}