package in.sp.main.dto;

import java.util.Set;


public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String country; 
    private String contact;// Added country field
    private Set<String> roles;
    private Set<ComplaintDTO> complaints;  // Added mapping for complaints
    
	public UserDTO(Long id, String username, String email, String contact, String country, Set<String> roles,
			Set<ComplaintDTO> complaints) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.country = country;
		this.contact = contact;
		this.roles = roles;
		this.complaints = complaints;
	}
	
	

//	public UserDTO(Long id, String username, String email, String password, String country, Set<String> roles,
//			Set<ComplaintDTO> complaints) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.email = email;
//		this.password = password;
//		this.country = country;
//		this.roles = roles;
//		this.complaints = complaints;
//	}



	public UserDTO() {
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
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
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public Set<ComplaintDTO> getComplaints() {
		return complaints;
	}
	public void setComplaints(Set<ComplaintDTO> complaints) {
		this.complaints = complaints;
	}

    // Constructor, Getters and Setters
}

