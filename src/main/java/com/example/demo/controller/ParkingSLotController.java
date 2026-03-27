package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.model.ParkingSlot;
import com.example.demo.service.ParkingSlotService;
import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingSLotController {

    private final ParkingSlotService ps;

   public ParkingSLotController(ParkingSlotService ps) {
        this.ps = ps;
    }

    // ---------- CREATE ----------
    @PostMapping("/create")
    public ParkingSlot createSlot(@RequestBody ParkingSlot slot) {
        return ps.createSlot(slot);
    }

    // ---------- GET ALL ----------
    @GetMapping("/all")
    public List<ParkingSlot> getAllSlots() {
        return ps.getAllSlots();
    }

    // ---------- GET BY TYPE ----------
    @GetMapping("/type/{type}")
    public ParkingSlot getByType(@PathVariable String type) {
        return ps.getParkingSlotByType(type);
    }

    // ---------- GET BY LOCATION ----------
    @GetMapping("/location/{location}")
    public ParkingSlot getByLocation(@PathVariable String location) {
        return ps.getParkinglotByLocation(location);
    }

    // ---------- GET AVAILABLE SLOTS ----------
   @GetMapping("/available")
public List<ParkingSlot> getAvailableSlots() {
    return ps.getAvailableSlots();   // ⭐ FIXED
}


    // ---------- UPDATE ----------
    @PutMapping("/update/{id}")
    public ParkingSlot updateSlot(@RequestBody ParkingSlot slot, @PathVariable Long id) {
        return ps.updateSlotDetails(slot, id);
    }

   
    @DeleteMapping("/delete/{slotNumber}")
    public void deleteSlot(@PathVariable String slotNumber) {
        ps.deleteslots(slotNumber);
    }
}
