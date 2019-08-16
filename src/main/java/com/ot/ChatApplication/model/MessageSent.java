package com.ot.ChatApplication.model;

import java.time.LocalDateTime;

public class MessageSent {
	
	String username;
	String message;
	LocalDateTime timeStamp;
	
	public MessageSent(String username, String message, LocalDateTime timeStamp) {
		super();
		this.username = username;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public String getUsername() {
		return username;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	@Override
	public String toString() {
		return username+" "+message+" "+timeStamp;
	}
	
}
