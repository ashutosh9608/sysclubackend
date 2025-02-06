package in.sp.main.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entity.User;
import java.util.List;



public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String username);
    
    boolean existsByUsername(String username);
    
    User findByOtp(String otp);
    
	User findByUsername(String username);

	User findByEmail(String usernameOrEmail);

	
}



