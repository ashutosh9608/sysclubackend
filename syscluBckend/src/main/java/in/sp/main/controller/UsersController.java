package in.sp.main.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.dto.ComplaintDTO;
import in.sp.main.service.UserService;

@RestController
@RequestMapping("api/users")
public class UsersController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/savecomplaints")
	public ResponseEntity<String> saveComplaints(@RequestBody ComplaintDTO complaintDto){
		userService.addComplaint(complaintDto);
		return ResponseEntity.ok("Complaint Registered Successfully");
	}
	
}
