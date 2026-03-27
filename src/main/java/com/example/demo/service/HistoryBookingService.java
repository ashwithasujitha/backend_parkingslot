package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Booking;
import com.example.demo.model.HistoryBooking;
import com.example.demo.repository.HistoryBookingRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class HistoryBookingService {

    private final HistoryBookingRepository hr;

    // ✅ Only one constructor — public — Spring can inject it.
    public HistoryBookingService(HistoryBookingRepository hr) {
        this.hr = hr;
    }

    public Optional<HistoryBooking> getHistoryById(Long id) {
        return hr.findById(id);
    }

    public List<HistoryBooking> getByBooking(Booking booking) {
        return hr.findByBooking(booking);
    }

    @Transactional
    public void deleteById(Long historyId) {
        if (!hr.existsById(historyId)) {
            throw new EntityNotFoundException("HistoryBooking not found: " + historyId);
        }
        hr.deleteById(historyId);
    }
}
