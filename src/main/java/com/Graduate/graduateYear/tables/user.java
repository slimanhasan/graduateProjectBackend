package com.Graduate.graduateYear.tables;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

@Entity
@Validated
public class user {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@NotEmpty
	@NotNull
	String email;
	
	@NotEmpty
	@NotNull
	String password;
	
	@NotEmpty
	@NotNull
	String userName;
	
	@NotEmpty
	@NotNull
	String location;
	
	 @NotNull()
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date date;
	
	@NotEmpty
	@NotNull
	@Size(max = 10 , min=10)
	String phone;
	
	String img;

	public user() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public user( @NotEmpty @NotNull String email, @NotEmpty @NotNull String password,
			@NotEmpty @NotNull String userName, @NotEmpty @NotNull String location, @NotNull Date date,
			@NotEmpty @NotNull @Size(max = 10, min = 10) String phone) {
		super();
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.location = location;
		this.date = date;
		this.phone = phone;
	}
	
}