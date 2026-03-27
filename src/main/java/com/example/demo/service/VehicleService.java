package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.demo.model.User;
import com.example.demo.model.Vehicle;

import com.example.demo.repository.VehicleRepository;

@Service
public class VehicleService {
    
    private final VehicleRepository vr;
    public  VehicleService(VehicleRepository vr)
    {
        this.vr=vr;
     
    }

    public Vehicle createVehicle(Vehicle v)
    {
           return vr.save(v);
    }
    public List<Vehicle> getVehicleNo(String vehicleno)
    {
        return vr.findByVehicleno(vehicleno);
    }
    public Optional<Vehicle> getVehicleById(Long id)
    {
        return vr.findById(id);
    }
    public List<Vehicle> getVehicleByUser(User user)
    {
        return vr.findByUser(user);
    }
    public Vehicle updateVehicle(Vehicle v,Long id)
    {
        Vehicle vehicle=vr.findById(id).orElseThrow(()-> new ResourceAccessException("Vehicle not found"));
        vehicle.setVehicleType(v.getVehicleType());
        vehicle.setVehicleno(v.getVehicleno());
        vehicle.setUser(v.getUser());
       return vr.save(vehicle);
        
    }
    public void deletById(Long id)
    {
        vr.deleteById(id);
    }
}
