package com.ot.ChatApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ot.ChatApplication.model.Message;

public interface MessageRepository extends JpaRepository<Message,Long>{

}
