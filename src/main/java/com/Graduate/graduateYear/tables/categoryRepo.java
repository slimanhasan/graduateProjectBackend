package com.Graduate.graduateYear.tables;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepo extends JpaRepository<Category,Integer>{

	Optional<Category>findByName(String categoryName);
	
}
