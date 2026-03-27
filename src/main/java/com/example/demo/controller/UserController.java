package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/register/users")
public class UserController {
    @Autowired
    private UserRepository ur;
    private final UserService us;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtutil;

    public UserController(UserService us) {
        this.us = us;
    }

    @Autowired
    private PasswordEncoder password;

    @PostMapping
    public ResponseEntity<User> Postsave(@RequestBody User u) {
        u.setPassword(password.encode(u.getPassword()));
        u.setRole(User.Role.user);  
        User saved = us.createUser(u);
        return ResponseEntity.status(201).body(saved);
    }
     @PostMapping("/login")
public ResponseEntity<Map<String, String>> login(@RequestBody User u) {
    try{
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword()));
        
        UserDetails userdetails = (UserDetails) auth.getPrincipal();
        String token = jwtutil.generateToken(userdetails);

       Optional<User> optionalUser = ur.findByUsername(u.getUsername());

if (optionalUser.isEmpty()) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("error", "User not found"));
}

User logged = optionalUser.get();

        if (logged == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
        }

        Map<String, String> response = Map.of(
                "token", token,
                "userId", String.valueOf(logged.getUserId()),
                "username", logged.getUsername(),
                "role", logged.getRole().name()
        );

        return ResponseEntity.ok(response);
    }
    catch(Exception e)
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error","Invalid username or password"));
    }
}

    @GetMapping
	public List<User> getUsers() {
//		return Arrays.asList(new User(1L, "Logesh", "logesh@gmail.com"));
		return ur.findAll();
    }
		

    @GetMapping("/{id}")

    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        Optional<User> got = us.getuser(id);
        return ResponseEntity.status(201).body(got);
    }

    @PutMapping("user/{id}")

    public ResponseEntity<User> updateUsers(@RequestBody User u, @PathVariable Long id) {
        User user = us.updateuser(u, id);
        return ResponseEntity.status(201).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteUser(@PathVariable Long id) {
        us.deletById(id);
        return ResponseEntity.ok().build();
    }

   

}
