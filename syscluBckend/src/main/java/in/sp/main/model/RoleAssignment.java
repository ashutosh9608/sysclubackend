package in.sp.main.model;

public class RoleAssignment {
	private String username;
	private String roleName;
	
	
	public RoleAssignment() {
		super();
	}


	public RoleAssignment(String username, String roleName) {
		super();
		this.username = username;
		this.roleName = roleName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
