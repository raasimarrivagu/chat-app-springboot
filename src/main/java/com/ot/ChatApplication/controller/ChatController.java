package com.ot.ChatApplication.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ot.ChatApplication.crypto.EncryptDecrypt;
import com.ot.ChatApplication.model.KeyTable;
import com.ot.ChatApplication.model.Message;
import com.ot.ChatApplication.model.MessageReceived;
import com.ot.ChatApplication.model.MessageSent;
import com.ot.ChatApplication.repository.KeyRepository;
import com.ot.ChatApplication.repository.MessageRepository;
import com.ot.ChatApplication.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping("/chat")

public class ChatController {
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	KeyRepository keyRepository;
	
	
	@PostMapping("/message")
	public Message messageReceived(@Valid @RequestBody MessageReceived messageReceived) {
		
		EncryptDecrypt cryption=new EncryptDecrypt();
		
		String messageSent=messageReceived.getMessage();
		KeyTable key=keyRepository.findByKeyName(messageReceived.getKeyName());
		long keyId=keyRepository.findByKeyName(messageReceived.getKeyName()).getKeyId();
		long userId=userRepository.findByName(messageReceived.getUserName()).getUserId();
		
		Map<String,String> map=new HashMap<>();
		map.put("message", messageSent);
		map.put("keyValue", key.getKeyValue());
		
		String encMessage=cryption.encryptMessage(map).get("message");
		
		Message message=new Message();
		
		message.setMessage(encMessage);
		message.setUserid(userId);
		message.setKeyid(keyId);
		
		return messageRepository.save(message);
		
	}
	
	@GetMapping("/mask")
	public List<MessageSent> getEncryptedForm(){
		System.out.println("mask called");
		List<MessageSent> messagesList=new ArrayList<MessageSent>();
		
		List<Message> messages = messageRepository.findAll();
		
		for(Message msg:messages) {
			
			MessageSent msgSent = new MessageSent(userRepository.findById(msg.getUserid()).get().getName(),msg.getMessage(),msg.getTimeStamp());
			
			messagesList.add(msgSent);
		}
		return messagesList;
	}
	
	@GetMapping("/show")
	public List<MessageSent> getDecryptedForm(){
		
		EncryptDecrypt cryption=new EncryptDecrypt();
		List<MessageSent> messagesList=new ArrayList<>();
		List<Message> messages=messageRepository.findAll();
		
		for(Message message:messages) {
			
			Map<String,String> map=new HashMap<>();
			map.put("message", message.getMessage());
			map.put("keyValue", keyRepository.findById(message.getKeyid()).get().getKeyValue());
			
			String decMessage=cryption.decryptMessage(map).get("message");
			
			MessageSent msgSent=new MessageSent(userRepository.findById(message.getUserid()).get().getName(),decMessage,message.getTimeStamp());
			
			messagesList.add(msgSent);
		}
		return messagesList;
	}
	
	@GetMapping("/getAllMessages")
	public List<Message> getAllMessages(){
		return messageRepository.findAll();
	}
}
