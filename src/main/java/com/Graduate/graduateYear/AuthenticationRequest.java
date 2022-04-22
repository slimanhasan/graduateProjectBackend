package com.Graduate.graduateYear;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class AuthenticationRequest {
	private String email;
	private String pass;

	public AuthenticationRequest(String email, String pass) {
		this.email = email;
		this.pass = pass;
	}

	public String getEmail() {
		return email;
	}

	public String getPass() {
		return pass;
	}

	
	
}
