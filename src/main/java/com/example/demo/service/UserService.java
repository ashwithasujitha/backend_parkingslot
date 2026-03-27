package com.example.demo.service;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {


    private final UserRepository ur;
    public  UserService(UserRepository ur)
    {
        this.ur=ur;
     
    }
   

    public User createUser(User u)
    {
           return ur.save(u);
    }
    public Optional<User> getuser(Long id)
    {
        return ur.findById(id);
    }
    public User updateuser(User u,Long id)
    {
        User user=ur.findById(id).orElseThrow(()-> new ResourceAccessException("User not found"));
        user.setUsername(u.getUsername());
        user.setEmail(u.getEmail());
        user.setPassword(u.getPassword());
        user.setPhno(u.getPhno());
        user.setVehicleNo(u.getVehicleNo());
       return ur.save(user);
        
    }
    public void deletById(Long id)
    {
        ur.deleteById(id);
    }
    // @Bean
    // public PasswordEncoder passwordEncoder(){
    //     return new BCryptPasswordEncoder();
    // }
}
