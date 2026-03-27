package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="parking_slot")
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="slot_id")
      private Long id;
      
      @Column(nullable = false, unique = true)
      private String slotNumber;
      
      private String type;
      private Boolean isAvailable;
      private Double pricePerHour;
      private String location;
      @ManyToOne
      @JoinColumn(name="parking_lot_id",nullable = false)
      private Parkinglot parkingLot;
      private String slotName;  

public String getSlotName() {
    return slotName;
  }
  public void setSlotName(String slotName) {
    this.slotName = slotName;
  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getSlotNumber() {
    return slotNumber;
  }
  public void setSlotNumber(String slotNumber) {
    this.slotNumber = slotNumber;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  
  public Boolean getIsAvailable() {
    return isAvailable;
  }
  public void setIsAvailable(Boolean isAvailable) {
    this.isAvailable = isAvailable;
  }
  public Double getPricePerHour() {
    return pricePerHour;
  }
  public void setPricePerHour(Double pricePerHour) {
    this.pricePerHour = pricePerHour;
  }
  public String getLocation() {
    return location;
  }
  public void setLocation(String location) {
    this.location = location;
  }
  public Parkinglot getParkingLot() {
    return parkingLot;
  }
  public void setParkingLot(Parkinglot parkingLot) {
    this.parkingLot = parkingLot;
  }
  

   

    /**
     * @return Boolean return the isAvailable
     */
    public Boolean isIsAvailable() {
        return isAvailable;
    }

}
