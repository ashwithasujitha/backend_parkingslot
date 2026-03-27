package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.VehicleDto;
import com.example.demo.model.User;
import com.example.demo.model.Vehicle;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.service.VehicleService;

@Controller
@RequestMapping("/api/vehicle")
public class vehicleController {
    private final VehicleService vs;
    private VehicleRepository vehicleRepository;
    private VehicleDto vehicleDTO;
    private User user;
    private UserRepository userRepository;
   
  private final VehicleService vehicleService;

    public vehicleController(VehicleService vs,UserRepository userRepository,VehicleRepository vehicleRepository) {
        this.vs = vs;
        this.userRepository=userRepository;
        this.vehicleRepository=vehicleRepository;
        this.vehicleService = null;
    }
   
   @PostMapping("/post")
public ResponseEntity<String> saveVehicle(@RequestBody VehicleDto dto) {
try{
    User user = userRepository.findById(dto.getUserId())
                     .orElseThrow(() -> new RuntimeException("User not found"));

    Vehicle v = new Vehicle();
    v.setUser(user);
    v.setVehicleno(dto.getVehicleno());
    v.setVehicleType(dto.getVehicleType());
    v.setModel(dto.getModel());

   
     vehicleRepository.save(v);

        return ResponseEntity.ok("Vehicle saved successfully");
    
}
    catch(Exception e){
        return ResponseEntity.status(400).body("Failed: " + e.getMessage());
    }
}

    @GetMapping("/get/{id}")
    public Optional<Vehicle> getVehicle(@PathVariable Long id)
    {
        return vs.getVehicleById(id);
    }
    public List<Vehicle>getVehicles(@PathVariable String vehicleno)
    {
        return vs.getVehicleNo(vehicleno);
    }
    @GetMapping("/get/{user}")
    public List<Vehicle>getVehiclesByUSer(@PathVariable User u)
    {
        return vs.getVehicleByUser(u);
    }
    @PutMapping("/vehicle/{user}")
    public Vehicle updated(@RequestBody Vehicle v, @PathVariable Long id){
       return vs.updateVehicle(v,id);
    }
    @DeleteMapping("/id")
    public void deleted(@PathVariable Long id){
         vs.deletById(id);
    }
}
