// package com.example.demo.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.web.bind.annotation.*;

// import com.example.demo.dto.UserLoginRequest;
// import com.example.demo.dto.UserREgisterationRequest;
// import com.example.demo.dto.UserResponse;
// import com.example.demo.model.User;
// import com.example.demo.security.JwtUtil;
// import com.example.demo.service.UserService;


// import java.util.Map;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     @Autowired
//     private AuthenticationManager authenticationManager;

//     @Autowired
//     private JwtUtil jwtUtil;

//     @Autowired
//     private UserService userService;

//     @PostMapping("/login")
//     public ResponseEntity<Map<String, String>> login(@RequestBody UserLoginRequest request) {
//         try {
//             Authentication auth = authenticationManager
//                     .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
//             UserDetails userDetails = (UserDetails) auth.getPrincipal();
//             String token = jwtUtil.generateToken(userDetails);
//             return ResponseEntity.ok(Map.of("token", token));
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid username or password"));
//         }
//     }

//     @PostMapping("/register")
//     public ResponseEntity<?> register(@RequestBody UserREgisterationRequest request) {
//         User saved = userService.createUser(request);
//        UserResponse response = userService.convertToResponseDto(saved);
//         return ResponseEntity.status(HttpStatus.CREATED).body(response);
//     }
// }
