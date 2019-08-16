package com.ot.ChatApplication.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="message")
@JsonIgnoreProperties(value = {"timeStamp"}, allowGetters = true)
public class Message {

	@Id
	@GeneratedValue
	long msgid;
	
	@Column
	String message;
	
	@CreationTimestamp
	@Column(updatable=false,name="timestamp")
	LocalDateTime timeStamp;
	
	long userid;
	
	long keyid;

	public long getMsgid() {
		return msgid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public long getUserid() {
		return userid;
	}
	
	public void setUserid(long userid) {
		this.userid=userid;
	}

	public long getKeyid() {
		return keyid;
	}
	
	public void setKeyid(long keyid) {
		this.keyid=keyid;
	}

}
