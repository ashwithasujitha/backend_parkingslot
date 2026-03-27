package com.example.demo.dto;


public class VehicleDto {
    private Long userId;
    private String vehicleType;
    private String vehicleno;
    private String model;
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public String getVehicleno() {
        return vehicleno;
    }
    public void setVehicleno(String vehicleno) {
        this.vehicleno = vehicleno;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
}