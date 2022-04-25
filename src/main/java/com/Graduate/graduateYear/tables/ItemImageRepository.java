package com.Graduate.graduateYear.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemImageRepository extends JpaRepository<itemImages, Integer>{

}
