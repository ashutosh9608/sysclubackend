package in.sp.main.dto;

import java.time.LocalDateTime;

public class ReplyDTO {
    private Long id;
    private String replyText;
    private LocalDateTime dateReplied;
    private Long adminId;
    
    

	public ReplyDTO(Long id, String replyText, LocalDateTime dateReplied, Long adminId) {
		super();
		this.id = id;
		this.replyText = replyText;
		this.dateReplied = dateReplied;
		this.adminId = adminId;
	}
	
	public ReplyDTO() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReplyText() {
		return replyText;
	}
	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}
	public LocalDateTime getDateReplied() {
		return dateReplied;
	}
	public void setDateReplied(LocalDateTime dateReplied) {
		this.dateReplied = dateReplied;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

    // Constructor, Getters and Setters
}
