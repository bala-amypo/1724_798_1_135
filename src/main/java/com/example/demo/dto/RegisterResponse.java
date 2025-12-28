package com.example.demo.dto;

public class RegisterResponse {
    private String fullName;
    private String email;
    private String password;
    private String role;

    
   public RegisterResponse(String fullName , String email, String role)
   {
       this.fullName = fullName;
       this.email = email;
       this.role = role;

   }

    
    public String getFullName() {
        return fullName;
    }
    
   
    
    public String getEmail() {
        return email;
    }
    

    public String getRole() {
        return role;
    }
    
   
}