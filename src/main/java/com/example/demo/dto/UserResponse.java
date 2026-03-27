package com.example.demo.dto;



import jakarta.persistence.Column;

public class UserResponse {
    @Column(name="user_id")
     private Long userId;
     
    public Long getUserId() {
        return userId;
    }
     public void setUserId(Long userId) {
         this.userId = userId;
     }
     public String getUsername() {
         return username;
     }
     public void setUsername(String username) {
         this.username = username;
     }
     public String getEmail() {
         return email;
     }
     public void setEmail(String email) {
         this.email = email;
     }
     public Long getPhno() {
         return phno;
     }
     public void setPhno(Long phno) {
         this.phno = phno;
     }
     public String getRole() {
         return role;
     }
     public void setRole(String role) {
         this.role = role;
     }
    private String username;
    private String email;
    private Long phno;
    private String role;
    
}
