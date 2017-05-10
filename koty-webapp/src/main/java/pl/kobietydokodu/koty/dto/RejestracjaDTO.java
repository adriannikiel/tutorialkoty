package pl.kobietydokodu.koty.dto;

import org.hibernate.validator.constraints.NotBlank;

public class RejestracjaDTO {
	
	@NotBlank
	private String user;
	
	@NotBlank
	private String pass;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
