package com.Graduate.graduateYear.tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Validated
public class item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@NotEmpty
	@NotNull
	String name;
	
	@JsonBackReference
	@NotNull
	@OneToOne()
	Category category;
	
	
	@JsonBackReference
	@NotNull
	@OneToOne()
	user author;
	
	
	@NotEmpty
	@NotNull
	String image,description;
	
	boolean took;
	public item() {}

	public item(@NotEmpty @NotNull String name, Category category, user author, @NotEmpty @NotNull String image,
			@NotEmpty @NotNull String description, boolean took ) {
		super();
		this.name = name;
		this.category = category;
		this.author = author;
		this.image = image;
		this.description = description;
		this.took = took;
		this.author=author;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public user getAuthor() {
		return author;
	}

	public void setAuthor(user author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isTook() {
		return took;
	}

	public void setTook(boolean took) {
		this.took = took;
	}
	
	
	
	
	
	
}
