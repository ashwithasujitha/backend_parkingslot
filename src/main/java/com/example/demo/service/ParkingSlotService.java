package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ParkingSlot;
import com.example.demo.repository.ParkingSlotRepository;

import jakarta.persistence.EntityNotFoundException;
@Service
public class ParkingSlotService {

    private final ParkingSlotRepository pr;

    @Autowired
    public ParkingSlotService(ParkingSlotRepository pr) {
        this.pr = pr;
    }

    public ParkingSlot createSlot(ParkingSlot ps) {
        return pr.save(ps);
    }

    public ParkingSlot getParkingSlotByType(String type) {
        return pr.findParkingSlotByType(type);
    }

    public ParkingSlot getParkinglotByLocation(String location) {
        return pr.findParkingSlotByLocation(location);
    }

    public List<ParkingSlot> getAvailableSlots() {
        return pr.findByIsAvailableTrue();   // ⭐ FIXED
    }

    public List<ParkingSlot> getAllSlots() {
        return pr.findAll();   // ⭐ FIXED (no more exception)
    }

    public ParkingSlot updateSlotDetails(ParkingSlot ps, Long id) {
        ParkingSlot slot = pr.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Slot not found " + id));

        slot.setType(ps.getType());
        slot.setIsAvailable(ps.getIsAvailable());
        slot.setLocation(ps.getLocation());

        return pr.save(slot);
    }

    public void deleteslots(String slotNumber) {
        pr.deleteBySlotNumber(slotNumber);
    }
}
