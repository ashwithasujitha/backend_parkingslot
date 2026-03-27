package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.HistoryBooking;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.HistoryBookingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@CrossOrigin(origins = "http://localhost:3000")
public class HistoryBookingcontroller {

    private final HistoryBookingRepository historyRepo;
    private final BookingRepository bookingRepo;

    public HistoryBookingcontroller(HistoryBookingRepository historyRepo,
                                    BookingRepository bookingRepo) {
        this.historyRepo = historyRepo;
        this.bookingRepo = bookingRepo;
    }
    @GetMapping("/user/{userId}")
public List<HistoryBooking> getHistoryByUser(@PathVariable Long userId) {

    return historyRepo.findAll()
            .stream()
            .filter(h -> h.getBooking().getUser().getUserId().equals(userId))
            .toList();
}

    // GET all history by Booking ID
    @GetMapping("/booking/{bookingId}")
    public List<HistoryBooking> getHistoryByBooking(@PathVariable Long bookingId) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + bookingId));

        return historyRepo.findByBooking(booking);
    }
}
