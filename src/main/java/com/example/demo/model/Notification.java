package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private LocalDateTime sentAt;
    private String type; // e.g. "BOOKING", "CANCELLATION", "REMINDER"

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parking_lot_id")
    private Parkinglot parkingLot;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Parkinglot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(Parkinglot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    } 

}
