package com.ot.ChatApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ot.ChatApplication.model.KeyTable;

public interface KeyRepository extends JpaRepository<KeyTable,Long>{
	
	KeyTable findByKeyName(String keyname);

}
