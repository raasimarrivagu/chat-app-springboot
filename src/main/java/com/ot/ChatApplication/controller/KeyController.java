package com.ot.ChatApplication.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ot.ChatApplication.model.KeyTable;
import com.ot.ChatApplication.repository.KeyRepository;

@RestController
@CrossOrigin
@RequestMapping("/key")
public class KeyController {
	
	@Autowired
	KeyRepository keyRepository;
	
	@GetMapping("/getAllKeyNames")
	public List<String> getKeysByUID(){
		List<KeyTable> keys= keyRepository.findAll();
		List<String> keyNames=new ArrayList<>();
		
		for(KeyTable key:keys) {
			keyNames.add(key.getKeyName());
		}
		
		return keyNames;
	}

	@GetMapping("/getAllKeys")
	public List<KeyTable> getKeys(){
		return keyRepository.findAll();
	}
	
	@PostMapping("/generateKey")
	public KeyTable getKeys(@RequestBody KeyTable key){
		return keyRepository.save(key);
	}
}
