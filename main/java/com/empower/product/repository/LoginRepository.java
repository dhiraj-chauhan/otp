package com.empower.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.empower.product.entity.Login;


@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

	Login findByUsername(String username);
	
//	  @Query("SELECT u FROM Login u WHERE u.username = :username")
//	    Login findByUsername(@Param("username") String username);
//	
//	}



}