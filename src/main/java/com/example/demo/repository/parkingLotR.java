package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Parkinglot;
@Repository
public interface parkingLotR extends JpaRepository<Parkinglot,Long>{
    List<Parkinglot> findByAddressContainingIgnoreCase(String address);

    List<Parkinglot> findByLotNameContainingIgnoreCase(String lotName);
}
