package in.sp.main.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class ComplaintDTO {
    private Long id;
    private String email;
    private String catogry;
    private String description;
    private LocalDateTime dateFiled;
    private Long complainantId;
    private Set<ReplyDTO> replies;  // Added mapping for replies
    
	public ComplaintDTO(Long id, String email, String catogry, String description, LocalDateTime dateFiled, Long complainantId,
			Set<ReplyDTO> replies) {
		super();
		this.id = id;
		this.email = email;
		this.catogry = catogry;
		this.description = description;
		this.dateFiled = dateFiled;
		this.complainantId = complainantId;
		this.replies = replies;
	}
	
	public ComplaintDTO() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public String getCatogry() {
		return catogry;
	}

	public void setCatogry(String catogry) {
		this.catogry = catogry;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDateTime getDateFiled() {
		return dateFiled;
	}
	public void setDateFiled(LocalDateTime dateFiled) {
		this.dateFiled = dateFiled;
	}
	public Long getComplainantId() {
		return complainantId;
	}
	public void setComplainantId(Long complainantId) {
		this.complainantId = complainantId;
	}
	public Set<ReplyDTO> getReplies() {
		return replies;
	}
	public void setReplies(Set<ReplyDTO> replies) {
		this.replies = replies;
	}
	
    // Constructor, Getters and Setters
}

