package com.Graduate.graduateYear.tables;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;


@Entity
@Validated
public class item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@NotEmpty
	@NotNull
	String name;
	
	@NotNull
	@OneToOne()
	Category category;
	
	
	@NotNull
	@OneToOne()
	user author;
	
	
	@NotEmpty
	@NotNull
	String description;
	
	boolean took;
	
	
	Date postDate;
	
	public item() {
		postDate=new Date();
	}

	public item(@NotEmpty @NotNull String name, Category category, user author,
			@NotEmpty @NotNull String description, boolean took ) {
		super();
		this.name = name;
		this.category = category;
		this.author = author;
		this.description = description;
		this.took = took;
		this.author=author;
		this.postDate=new Date();
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

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	
	
	
	
	
	
	
}
