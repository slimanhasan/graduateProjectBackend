package com.Graduate.graduateYear.tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Entity
@Validated
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int  id;
	
	
	@NotEmpty
	@NotNull
	private String name;
	
	private String img;
	public Category() {}
	public Category(@NotEmpty @NotNull String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
