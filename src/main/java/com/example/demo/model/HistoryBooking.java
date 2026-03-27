package com.example.demo.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class HistoryBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "booking_id")
    @JsonBackReference("booking-history")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "changed_by")
    @JsonBackReference("user-histories")
    private User changedBy;

    @Column(nullable = false, length = 50)
    private String statusChange;

    @Column(length = 50)
    private String previousStatus;

    @Column(length = 50)
    private String newStatus;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime changeDate;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(length = 200)
    private String reason;

    // GETTERS + SETTERS
    public Long getHistoryId() { return historyId; }
    public void setHistoryId(Long historyId) { this.historyId = historyId; }

    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }

    public User getChangedBy() { return changedBy; }
    public void setChangedBy(User changedBy) { this.changedBy = changedBy; }

    public String getStatusChange() { return statusChange; }
    public void setStatusChange(String statusChange) { this.statusChange = statusChange; }

    public String getPreviousStatus() { return previousStatus; }
    public void setPreviousStatus(String previousStatus) { this.previousStatus = previousStatus; }

    public String getNewStatus() { return newStatus; }
    public void setNewStatus(String newStatus) { this.newStatus = newStatus; }

    public LocalDateTime getChangeDate() { return changeDate; }
    public void setChangeDate(LocalDateTime changeDate) { this.changeDate = changeDate; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
