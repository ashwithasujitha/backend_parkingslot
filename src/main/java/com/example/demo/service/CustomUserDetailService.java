package com.example.demo.service;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Component
public class CustomUserDetailService implements UserDetailsService{
    @Autowired
    private UserRepository userrepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
 User user = userrepository.findByEmail(email)
            .orElseThrow(() ->new UsernameNotFoundException("User not found: " + email)
            );
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(),
            user.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority("USER"))
        );
    }

    
}
