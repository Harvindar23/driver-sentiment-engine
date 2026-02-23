package com.example.driver.config;

import com.example.driver.model.Feedback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class AppConfig {

    @Bean
    public BlockingQueue<Feedback> feedbackQueue() {
        return new LinkedBlockingQueue<>();
    }
}