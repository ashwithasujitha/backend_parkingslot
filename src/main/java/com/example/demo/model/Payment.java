package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long paymentId;

    private Double amount;

    private String paymentMethod; // e.g. "CREDIT_CARD", "UPI", "CASH"

    private String status; // e.g. "SUCCESS", "FAILED", "PENDING"

    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;
}
