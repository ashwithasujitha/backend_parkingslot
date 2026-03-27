package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepo;
    private final ParkingSlotRepository slotRepo;
    private final BookingRepository bookingRepo;
    private final PaymentRepository paymentRepo;

    public AdminService(
            UserRepository userRepo,
            ParkingSlotRepository slotRepo,
            BookingRepository bookingRepo,
            PaymentRepository paymentRepo
    ) {
        this.userRepo = userRepo;
        this.slotRepo = slotRepo;
        this.bookingRepo = bookingRepo;
        this.paymentRepo = paymentRepo;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public List<ParkingSlot> getAllSlots() {
        return slotRepo.findAll();
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }
}
