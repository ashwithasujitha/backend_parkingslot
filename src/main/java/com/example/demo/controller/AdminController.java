package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.service.AdminService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

   
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

  
    @GetMapping("/slots")
    public List<ParkingSlot> getAllSlots() {
        return adminService.getAllSlots();
    }

   
    @GetMapping("/bookings")
    public List<Booking> getAllBookings() {
        return adminService.getAllBookings();
    }

   
    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        return adminService.getAllPayments();
    }
}
