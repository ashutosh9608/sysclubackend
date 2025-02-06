package in.sp.main.serviceImpl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.sp.main.entity.User;
import in.sp.main.repository.UserRepository;
import in.sp.main.service.EmailService;

@Service
public class PasswordService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Password encoder bean
    
    // Generate a reset token and send email
    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email);
        
        if(user == null) {
        	throw new RuntimeException("no user found: "+email);
        }else {
        // Generate reset token
        	String otp = generateOTP();
            user.setOtp(otp);
            user.setOtpExpirationTime(LocalDateTime.now().plusMinutes(10)); // OTP valid for 10 minutes
            userRepository.save(user);
        // Send email with reset link
//        String resetUrl = "http://localhost:8080/api/reset-password?token=" + token;
        String resetUrl = otp;
        emailService.sendEmail(email, "Password Reset Request", "Your OTP to reset your password is " + resetUrl);
        }
    }

    // Reset the password
    public void resetPassword(String otp ,String email ,String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("No user found with email: " + email);
        }
        if (!otp.equals(user.getOtp()) || user.getOtpExpirationTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invalid or expired OTP");
        }

        // Update password (hashing recommended)
        user.setPassword(passwordEncoder.encode(newPassword)); // Use BCrypt for hashing
        user.setOtp(null); // Clear token
        user.setOtpExpirationTime(null);
        userRepository.save(user);
    }
    
    public String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate a 6-digit number
        return String.valueOf(otp);
    }
}
