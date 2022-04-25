package com.Graduate.graduateYear.tables;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<user,Integer>{

	Optional<user> findByEmail(String email);
	
	@Transactional
	@Modifying
	@Query(value = "update user u set u.img=:url where u.email=:email")
	void updateImg(@Param("email")String email,@Param("url")String url);
}
