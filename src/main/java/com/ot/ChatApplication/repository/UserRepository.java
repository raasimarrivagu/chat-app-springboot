package com.ot.ChatApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ot.ChatApplication.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	User findByName(String name);

}
