package in.sp.main.model;

import java.util.Set;

import in.sp.main.dto.ComplaintDTO;

public class LoginResponse {

    private String username;
    private String email;
    private String country;  // Added country field
    private String token;
    private String contact;
    private Set<String> roles;
    private Set<ComplaintDTO> complaints;  // Added mapping for complaints
    
	public LoginResponse() {
		super();
	}
	
	public LoginResponse(String username, String email, String country, String contact, String token , Set<String> roles,
			Set<ComplaintDTO> complaints) {
		super();
		this.username = username;
		this.email = email;
		this.country = country;
		this.contact = contact;
		this.token = token;
		this.roles = roles;
		this.complaints = complaints;
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
	public String getCountry() {
		return country;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
    
    
}
