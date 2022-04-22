package com.Graduate.graduateYear.tables;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface itemRepository extends JpaRepository<item, Integer> {

	List<item> findByCategory(Category category);
	
	@Transactional
	@Query(value = "select i from item i where i.category = :categ and i.name like CONCAT('%',:itemName,'%')")
	List<item> findItemsWithPartOfNameAndCategory(@Param("itemName")String itemName,@Param("categ")Category cat);
}
