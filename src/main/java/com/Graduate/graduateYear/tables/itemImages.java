package com.Graduate.graduateYear.tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class itemImages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	
	@NotBlank
	String image;
	
	@ManyToOne
	item i;

	public itemImages() {}
	
	public itemImages(int id, @NotBlank String image, item i) {
		super();
		this.id = id;
		this.image = image;
		this.i = i;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public item getI() {
		return i;
	}

	public void setI(item i) {
		this.i = i;
	}
	
	
	
	

}
