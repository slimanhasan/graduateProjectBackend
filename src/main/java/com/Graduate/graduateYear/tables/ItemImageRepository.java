package com.Graduate.graduateYear.tables;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemImageRepository extends JpaRepository<itemImages, Integer>{

	List<itemImages> findByI(item i);
}
