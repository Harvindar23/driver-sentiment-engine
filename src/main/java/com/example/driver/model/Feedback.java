package com.example.driver.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String driverId;
    private String employeeId;

    @Column(length = 1000)
    private String text;

    private LocalDateTime timestamp;

    // Required by JPA
    public Feedback() {
    }

    public Feedback(String driverId, String employeeId, String text) {
        this.driverId = driverId;
        this.employeeId = employeeId;
        this.text = text;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getDriverId() { return driverId; }
    public String getEmployeeId() { return employeeId; }
    public String getText() { return text; }
    public LocalDateTime getTimestamp() { return timestamp; }
}