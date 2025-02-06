package in.sp.main.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import in.sp.main.dto.ComplaintDTO;
import in.sp.main.dto.ReplyDTO;
import in.sp.main.dto.UserDTO;
import in.sp.main.entity.Complaint;
import in.sp.main.entity.Role;
import in.sp.main.entity.User;
import in.sp.main.mapper.MapToDTO;
import in.sp.main.model.LoginResponse;
import in.sp.main.model.RoleAssignment;
import in.sp.main.repository.RoleRepository;
import in.sp.main.repository.UserRepository;
import in.sp.main.service.EmailService;
import in.sp.main.service.JWTService;
import in.sp.main.service.UserService;

@Service
class UserServiceImpl implements UserService {

	@Autowired
	MapToDTO mapToDto;

	@Autowired
	JWTService jwtService;

	@Autowired
	private EmailService emailService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	public String registerVerificationEmailSent(String email, String username) {
		User user = userRepository.findByEmail(email);

		if (user == null) {
			user = userRepository.findByUsername(username);
		}

		if (user != null) {
			String password = user.getPassword();
			if (password != null && user.getUsername().equals(username)) {
				throw new RuntimeException("Please Enter Unique username");
			} else if (password != null && user.getEmail().equals(email)) {
				throw new RuntimeException("Email already registered");
			} else if (password == null) {
				String otp = new PasswordService().generateOTP();
				user.setEmail(email);
				user.setUsername(username);
				user.setOtp(otp);
				user.setOtpExpirationTime(LocalDateTime.now().plusMinutes(10));
				emailService.sendEmail(email, "OTP verification of registration",
						"Please enter this OTP to verify yourSelf as real user" + otp);
				userRepository.save(user);
			}
		} else {
			String otp = new PasswordService().generateOTP();
			User userdet = new User();
			userdet.setEmail(email);
			userdet.setUsername(username);
			userdet.setOtp(otp);
			userdet.setOtpExpirationTime(LocalDateTime.now().plusMinutes(10));
			emailService.sendEmail(email, "OTP verification of registration",
					"Please enter this OTP to verify yourSelf as real user" + otp);
			userRepository.save(userdet);
		}
		return "Something went wrong";
	}

	@Override
	public void registerUser(User user) {
		userRepository.save(user);
		String username = user.getUsername();
		String email = user.getEmail();
		emailService.sendEmail(email, "WELCOME " + username,
				"Sysclu appreciate your ideas and our oranisation feels an honour to work and support your ideas");
	}

	@Override
	public LoginResponse verify(User request) {
		String usernameOrEmail = request.getUsername(); // The user input (username or email)

		try {
			// Check if the input is an email
			String username = isEmail(usernameOrEmail) ? userRepository.findByEmail(usernameOrEmail).getUsername() // Fetch
					: usernameOrEmail;

			// Authenticate the user
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));

			// If authentication is successful, generate and return the JWT token
			if (authentication.isAuthenticated()) {
				String token = jwtService.generateToken(username);
				User user = userRepository.findByUsername(username);
				return new LoginResponse(user.getUsername(), user.getEmail(), user.getCountry(), user.getContact(),
						token, user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()),
						user.getComplaints().stream()
								.map(complaint -> new ComplaintDTO(complaint.getId(),null,complaint.getCatogry(), complaint.getDescription(),
										complaint.getDateFiled(), complaint.getComplainant().getId(),
										complaint.getReplies().stream()
												.map(reply -> new ReplyDTO(reply.getId(), reply.getReplyText(),
														reply.getDateReplied(), reply.getAdmin().getId()))
												.collect(Collectors.toSet())))
								.collect(Collectors.toSet()));
			}
		} catch (Exception e) {
			throw new RuntimeException("Invalid credentials or user not found");
		}

		throw new RuntimeException("Authentication failed");
	}

	private boolean isEmail(String usernameOrEmail) {
		// TODO Auto-generated method stub
		return usernameOrEmail.contains("@");
	}

	@Override
	public String assignRole(RoleAssignment roleAssignmentrequest) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(roleAssignmentrequest.getUsername());
		if (user == null) {
			throw new RuntimeException("User not found");
		}
		Role role = roleRepository.findByName(roleAssignmentrequest.getRoleName());
		if (role == null) {
			throw new RuntimeException("Role not found");
		}

		user.getRoles().add(role);
		userRepository.save(user);
		return "Role assigned successfully";
	}

	@Override
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			throw new RuntimeException("No users found");
		}
		List<UserDTO> users_list = users.stream().map((user) -> mapToDto.convertToUserDTO(user))
				.collect(Collectors.toList());

		return users_list;
	}

	@Override
	public void addComplaint(ComplaintDTO complaintDto) {
		User user = userRepository.findByEmail(complaintDto.getEmail());
		if(user == null) {
			throw new RuntimeException("Internal Server Error");
		}else {
			Set<Complaint> complaints = user.getComplaints();
			Complaint complaint = new Complaint();
			complaint.setCatogry(complaintDto.getCatogry());
			complaint.setDescription(complaintDto.getDescription());
			complaint.setDateFiled(LocalDateTime.now());
			complaint.setComplainant(user); // Set the complainant

		    // Add to user's complaints
		    user.getComplaints().add(complaint);

		    // Save user (complaint will be saved due to CascadeType.ALL)
		    userRepository.save(user);
		}
		
	}

}
