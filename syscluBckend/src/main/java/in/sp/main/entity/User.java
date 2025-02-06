package in.sp.main.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true) // Ensure email is unique
    private String username;
    private String password;
    private String email;
    private String country;  
    private String contact;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "user_roles", 
      joinColumns = @JoinColumn(name = "user_id"), 
      inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "complainant")
    private Set<Complaint> complaints = new HashSet<>();// Added mapping for complaints
    
    @Column(name = "otp")
    private String otp;

    @Column(name = "otp_expiration_time")
    private LocalDateTime otpExpirationTime;
    
    
	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public LocalDateTime getOtpExpirationTime() {
		return otpExpirationTime;
	}

	public void setOtpExpirationTime(LocalDateTime otpExpirationTime) {
		this.otpExpirationTime = otpExpirationTime;
	}

	public User(String password, String email, String contact, String otp, LocalDateTime otpExpirationTime) {
		super();
		this.password = password;
		this.email = email;
		this.otp = otp;
		this.otpExpirationTime = otpExpirationTime;
	}

	public User(Long id, String username, String password, String email, String contact, String country, Set<Role> roles,
			Set<Complaint> complaints) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.country = country;
		this.contact = contact;
		this.roles = roles;
		this.complaints = complaints;
	}
	

	public User() {
		// TODO Auto-generated constructor stub
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(Set<Complaint> complaints) {
		this.complaints = complaints;
	}

    // Getters and Setters  
}

