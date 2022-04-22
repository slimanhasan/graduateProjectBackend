package com.Graduate.graduateYear;

public class LoginResponse {
	private String token;
	private String username;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setToken(String token) {
		this.token=token;
	}
	public String getToken() {
		return this.token;
	}
	

}
