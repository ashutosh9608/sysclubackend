package in.sp.main.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String replyText;
    private LocalDateTime dateReplied;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;  // Mapped to the Complaint entity

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User admin;  // Mapped to the User entity (admin)

    
	public Reply() {
		super();
	}

	public Reply(Long id, String replyText, LocalDateTime dateReplied, Complaint complaint, User admin) {
		super();
		this.id = id;
		this.replyText = replyText;
		this.dateReplied = dateReplied;
		this.complaint = complaint;
		this.admin = admin;
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

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

    // Getters and Setters
    
}

