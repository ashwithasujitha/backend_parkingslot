package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ParkingSlot;



@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot,Long>{

    ParkingSlot findParkingSlotByType(String type);

    ParkingSlot findParkingSlotByIsAvailable(Boolean isAvailable);

    ParkingSlot findParkingSlotByLocation(String location);

    void deleteBySlotNumber(String slotNumber);

   Optional<ParkingSlot> findBySlotName(String slotName);
    List<ParkingSlot> findByLocationContainingIgnoreCase(String location);

 List<ParkingSlot> findByIsAvailableTrue(); 
    
} 
