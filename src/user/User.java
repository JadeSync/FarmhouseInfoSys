package user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	
	private String username, password, userrole, name;
	private Date userSince;
	private String[] modulesAuthorization;
	
	public User() {
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUserRole(String role) {
		this.userrole = role;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getUserRole() {
		return this.userrole;
	}
	
	public User( String username, String password, String userrole, String name ) {
		this.username = username;
		this.password = password;
		this.userrole = userrole;
		this.name = name;
	}
}
