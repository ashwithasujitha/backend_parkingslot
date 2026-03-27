package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
import com.example.demo.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository <Vehicle,Long>{

    List<Vehicle> findByVehicleno(String vehicleno);

   
    List<Vehicle> findByUser(User user);
}


   
    
