package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.BookingRequestDTO;
import com.example.demo.model.Booking;
import com.example.demo.model.ParkingSlot;
import com.example.demo.model.User;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.repository.ParkingSlotRepository;
import com.example.demo.service.BookingService;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin(origins = "http://localhost:3000")
public class BookingController {

    private final BookingService bookingService;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;
    private final ParkingSlotRepository slotRepository;

    public BookingController(BookingService bookingService,
                             UserRepository userRepository,
                             VehicleRepository vehicleRepository,
                             ParkingSlotRepository slotRepository) {

        this.bookingService = bookingService;
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.slotRepository = slotRepository;
    }
@PostMapping("/post")
public ResponseEntity<?> saveBooking(@RequestBody BookingRequestDTO req) {

    User user = userRepository.findById(req.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found: " + req.getUserId()));

    ParkingSlot slot = slotRepository.findBySlotName(req.getSlotName())
            .orElseThrow(() -> new RuntimeException("Slot not found: " + req.getSlotName()));

    // 🔹 Now this returns a List<Vehicle>
    List<Vehicle> vehicles = vehicleRepository.findByVehicleno(req.getVehicleNumber());

    Vehicle vehicle;

    if (vehicles.isEmpty()) {
        // No vehicle with this number yet → create new
        Vehicle v = new Vehicle();
        v.setVehicleType(req.getVehicleType());
        v.setVehicleno(req.getVehicleNumber());
        v.setUser(user);
        vehicle = vehicleRepository.save(v);
    } else {
        // At least one vehicle exists → reuse one (here, first)
        vehicle = vehicles.get(0);
        // If you want the most recent instead, you could sort by ID and pick last.
    }

    Booking booking = new Booking();
    booking.setUser(user);
    booking.setVehicle(vehicle);
    booking.setParkingSlot(slot);
    booking.setStartTime(req.getStartTime());
    booking.setEndTime(req.getEndTime());
    booking.setTotalCost(req.getTotalCost());
    booking.setStatus("BOOKED");

    bookingService.createBooking(booking);

    return ResponseEntity.ok("Booking Successful");
}


  

    
    @GetMapping("/get/{id}")
    public Optional<Booking> getBooking(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    
    @GetMapping("/user/{userId}")
    public List<Booking> getBookingsByUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return bookingService.getByUser(user);
    }

   
    @PutMapping("/{id}")
    public Booking updateBooking(@RequestBody Booking b, @PathVariable Long id) {
        return bookingService.UpdateBooking(b, id, null);
    }

    @DeleteMapping("/{id}")
public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
  //  historyBookingcontroller.de
    bookingService.DeleteById(id);
    return ResponseEntity.ok("Deleted successfully");
}

}
