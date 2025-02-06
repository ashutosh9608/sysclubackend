package in.sp.main.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String catogry;
    private String description;
    private LocalDateTime dateFiled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User complainant;  // Mapped to the User entity

    @OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL)
    private Set<Reply> replies = new HashSet<>();  // Mapped to the Reply entity

    
	public Complaint() {
		super();
	}

	public Complaint(Long id,String catogry, String description, LocalDateTime dateFiled, User complainant, Set<Reply> replies) {
		super();
		this.id = id;
		this.catogry = catogry;
		this.description = description;
		this.dateFiled = dateFiled;
		this.complainant = complainant;
		this.replies = replies;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCatogry() {
		return catogry;
	}

	public void setCatogry(String catogry) {
		this.catogry = catogry;
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

	public User getComplainant() {
		return complainant;
	}

	public void setComplainant(User complainant) {
		this.complainant = complainant;
	}

	public Set<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

    // Getters and Setters
    
}

