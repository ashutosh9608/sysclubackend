package in.sp.main.controller;

import java.time.LocalDateTime;
import java.util.Map;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import in.sp.main.entity.Role;
import in.sp.main.entity.User;
import in.sp.main.model.LoginResponse;
import in.sp.main.model.RoleAssignment;
import in.sp.main.repository.RoleRepository;
import in.sp.main.repository.UserRepository;
import in.sp.main.service.UserService;
import in.sp.main.serviceImpl.PasswordService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {

    @Autowired
    private UserService userService; // Service to handle user operations
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Password encoder bean
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordService passwordService;
    
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> payload) {
    	String email = payload.get("email");
        passwordService.forgotPassword(email);
        return ResponseEntity.ok("Password reset email sent successfully.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> payload) { 
    	
        passwordService.resetPassword(payload.get("otp") ,payload.get("mail") ,payload.get("newPassword"));
        return ResponseEntity.ok("Password reset successfully.");
    }
    
    @PostMapping("/register-verify")
    public ResponseEntity<String> registerVerificationEmailSent(@RequestBody Map<String, String> payload){
    	userService.registerVerificationEmailSent(payload.get("email"),payload.get("username"));
    	return ResponseEntity.ok("OTP is sent to Email"+payload.get("email"));
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> payload) {
       User preUser = userRepository.findByEmail(payload.get("email"));
       String otp = preUser.getOtp();
       if (!otp.equals(preUser.getOtp()) || preUser.getOtpExpirationTime().isBefore(LocalDateTime.now())) {
           throw new RuntimeException("Invalid or expired OTP");
       }
       if(otp != null) {
        User user = new User();
        Role userRole = roleRepository.findByName("USER");
        user.setCountry(payload.get("country"));
        user.setUsername(payload.get("username"));
        user.setPassword(passwordEncoder.encode(payload.get("password")));
        user.setEmail(payload.get("email"));
        user.getRoles().add(userRole);
        // Save the user
        userService.registerUser(user);
        return ResponseEntity.ok("User Registered successfully");
       }else {
    	   
    	   throw new RuntimeException("Enter Correct OTP");
       }
        
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginverification(@RequestBody User request) {
        //TODO: process POST request
        return ResponseEntity.ok(userService.verify(request));
    }
    
    @PostMapping("/assign-role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> assignRole(@RequestBody RoleAssignment roleAssignmentRequest) {
        //TODO: process POST request
        return ResponseEntity.ok(userService.assignRole(roleAssignmentRequest));
    }
    
    
}
