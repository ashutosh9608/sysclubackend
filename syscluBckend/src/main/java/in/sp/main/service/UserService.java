package in.sp.main.service;
import java.util.List;
import java.util.Map;

import in.sp.main.dto.ComplaintDTO;
import in.sp.main.dto.UserDTO;
import in.sp.main.entity.User;
import in.sp.main.model.LoginResponse;
import in.sp.main.model.RoleAssignment;

public interface UserService {
    boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	void registerUser(User request);
	LoginResponse verify(User request);
	String assignRole(RoleAssignment roleAssignmentRequest);
	List<UserDTO> getAllUsers();
	String registerVerificationEmailSent(String string, String string2);
	void addComplaint(ComplaintDTO complaintDto);
}
 