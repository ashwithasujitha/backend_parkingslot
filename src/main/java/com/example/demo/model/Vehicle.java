package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity

public class Vehicle {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="vehicle_id")
    private Long vehicleId;
    private String vehicleno;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    private String model;
    private String vehicleType;
    public Long getId() {
        return vehicleId;
    }
    public void setId(Long id) {
        this.vehicleId = id;
    }
    public String getVehicleno() {
        return vehicleno;
    }
    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    
    





    
}
