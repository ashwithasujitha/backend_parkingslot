package com.example.demo.service;

import com.example.demo.model.ParkingSlot;
import com.example.demo.model.Parkinglot;
import com.example.demo.repository.ParkingSlotRepository;
import com.example.demo.repository.parkingLotR;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SearchService {

    private final ParkingSlotRepository slotRepo;
    private final parkingLotR lotRepo;

    public SearchService(ParkingSlotRepository slotRepo, parkingLotR lotRepo) {
        this.slotRepo = slotRepo;
        this.lotRepo = lotRepo;
    }
@Transactional
    public List<Map<String, Object>> search(String location) {

        List<ParkingSlot> slots = slotRepo.findByLocationContainingIgnoreCase(location);

        // Use Set to avoid duplicates
        Set<Parkinglot> uniqueLots = new HashSet<>();

        for (ParkingSlot slot : slots) {
            uniqueLots.add(slot.getParkingLot());
        }

        List<Map<String, Object>> result = new ArrayList<>();

        for (Parkinglot lot : uniqueLots) {

            long availableSlots = lot.getSlots().stream()
                    .filter(ParkingSlot::getIsAvailable)
                    .count();

            double avgPrice = lot.getSlots().stream()
                    .mapToDouble(s -> s.getPricePerHour() != null ? s.getPricePerHour() : 0)
                    .average()
                    .orElse(0);

            Map<String, Object> map = new HashMap<>();
            map.put("lotId", lot.getId());
            map.put("lotName", lot.getLotName());
            map.put("address", lot.getAddress());
            map.put("capacity", lot.getCapacity());
            map.put("availableSlots", availableSlots);
            map.put("pricePerHour", avgPrice);

            result.add(map);
        }

        return result;
    }
}
