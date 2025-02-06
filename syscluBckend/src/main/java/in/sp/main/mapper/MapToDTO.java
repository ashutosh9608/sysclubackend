package in.sp.main.mapper;

import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;

import in.sp.main.dto.ComplaintDTO;
import in.sp.main.dto.ReplyDTO;
import in.sp.main.dto.UserDTO;
import in.sp.main.entity.Complaint;
import in.sp.main.entity.Reply;
import in.sp.main.entity.Role;
import in.sp.main.entity.User;

@Configuration
public class MapToDTO {
	public UserDTO convertToUserDTO(User user) {
	    UserDTO userDTO = new UserDTO();
	    userDTO.setId(user.getId());
	    userDTO.setUsername(user.getUsername());
	    userDTO.setEmail(user.getEmail());
	    userDTO.setContact(user.getContact());
	    userDTO.setCountry(user.getCountry());
	    userDTO.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
	    userDTO.setComplaints(user.getComplaints().stream().map(this::convertToComplaintDTO).collect(Collectors.toSet()));
	    return userDTO;
	}

	public ComplaintDTO convertToComplaintDTO(Complaint complaint) {
	    ComplaintDTO complaintDTO = new ComplaintDTO();
	    complaintDTO.setId(complaint.getId());
	    complaintDTO.setDescription(complaint.getDescription());
	    complaintDTO.setDateFiled(complaint.getDateFiled());
	    complaintDTO.setComplainantId(complaint.getComplainant().getId());
	    complaintDTO.setReplies(complaint.getReplies().stream().map(this::convertToReplyDTO).collect(Collectors.toSet()));
	    return complaintDTO;
	}
	
	public ReplyDTO convertToReplyDTO(Reply reply) {
	    ReplyDTO replyDTO = new ReplyDTO();
	    replyDTO.setId(reply.getId());
	    replyDTO.setReplyText(reply.getReplyText());
	    replyDTO.setDateReplied(reply.getDateReplied());
	    replyDTO.setAdminId(reply.getAdmin().getId());
	    return replyDTO;
	}




	

}
