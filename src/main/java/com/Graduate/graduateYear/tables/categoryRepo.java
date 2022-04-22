package com.Graduate.graduateYear.tables;


import org.springframework.data.jpa.repository.JpaRepository;

public interface categoryRepo extends JpaRepository<Category,Integer>{

	Category findByName(String categoryName);
	
}
