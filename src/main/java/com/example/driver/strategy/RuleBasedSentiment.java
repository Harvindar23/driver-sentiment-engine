package com.example.driver.strategy;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RuleBasedSentiment implements SentimentStrategy {

    private static final Set<String> POSITIVE_WORDS = Set.of(
            "good", "great", "safe", "polite", "smooth", "excellent", "nice"
    );

    private static final Set<String> NEGATIVE_WORDS = Set.of(
            "late", "rude", "unsafe", "bad", "dirty", "angry", "worst"
    );

    @Override
    public double analyze(String text) {

        if (text == null || text.isBlank()) {
            return 3.0; // neutral
        }

        text = text.toLowerCase();
        String[] words = text.split("\\W+");

        int score = 0;

        for (String word : words) {
            if (POSITIVE_WORDS.contains(word)) {
                score++;
            } else if (NEGATIVE_WORDS.contains(word)) {
                score--;
            }
        }

        if (score >= 3) return 5.0;
        if (score >= 1) return 4.0;
        if (score == 0) return 3.0;
        if (score <= -3) return 1.0;
        return 2.0;
    }
}