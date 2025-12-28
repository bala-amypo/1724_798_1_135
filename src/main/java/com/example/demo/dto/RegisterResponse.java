package com.example.demo.dto;
import com.example.demo.User;


public class RegisterResponse {
    private String fullName;
    private String email;
    private String password;
    private String role;
    private Long id;
    User user = new User();

    
   public RegisterResponse(Long id , String fullName , String email, String role)
   {
       this.id = user.get
       this.fullName = fullName;
       this.email = email;
       this.role = role;

   }
     
    public String getId()
    {
        return id;
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