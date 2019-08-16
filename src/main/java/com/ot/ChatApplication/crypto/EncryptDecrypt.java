package com.ot.ChatApplication.crypto;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ot.ChatApplication.repository.KeyRepository;

@RestController
@RequestMapping("/crypt")
public class EncryptDecrypt {
	
	String message;
	String key;
	
	@Autowired
	KeyRepository keyRepository;
	
	@PostMapping("/encrypt2")
	public Map<String,String> encryptt(@RequestParam("plaintext") String message, @RequestParam("keyname") String keyName)
	{
		Map<String,String> map=new HashMap<>();
		map.put("message",message);
		map.put("keyName",keyName);
		
		return encryptMessage(map);
	}
	
	@PostMapping("/decrypt2")
	public Map<String,String> decryptt(@RequestParam("cipertext") String message, @RequestParam("keyname") String keyName)
	{
		Map<String,String> map=new HashMap<>();
		map.put("message",message);
		map.put("keyName", keyName);
		
		return decryptMessage(map);
	}
	
	@PostMapping("/encrypt")
	public Map<String,String> encryptMessage(@RequestBody Map<String,String> map) {
		
		String resultMessage="";
		String message=map.get("message");
		String key="";
		
		if(map.containsKey("keyValue")) {
			key=map.get("keyValue");
		}else {
			String keyName=map.get("keyName");
			key=keyRepository.findByKeyName(keyName).getKeyValue();
		}
		
		System.out.println("--------------ENCRYPT--------------");
		System.out.println("message:"+message);
		System.out.println("keyvalue:"+key);
		System.out.println("====================================");
		
		
			int k=0;
		for(int i=0;i<message.length();i++) {
			if(k==key.length())k=0;
			int asciiValue=((int)message.charAt(i)+(int)key.charAt(k))%256;
			resultMessage+=(char)(asciiValue);
			k++;
		}
		
		
		HashMap<String,String> hm=new HashMap<>();
		hm.put("message", resultMessage);
		return hm;
	}
	
	@PostMapping("/decrypt")
	public Map<String,String> decryptMessage(@RequestBody Map<String,String> map) {
		
		String resultMessage="";
		String message=map.get("message");
		String key="";
		
		if(map.containsKey("keyValue")) {
			key=map.get("keyValue");
		}else {
			String keyName=map.get("keyName");
			key=keyRepository.findByKeyName(keyName).getKeyValue();
		}
		
		System.out.println("--------------DECRYPT--------------");
		System.out.println("message:"+message);
		System.out.println("keyvalue:"+key);
		System.out.println("====================================");
		
		
			int k=0;
			
		for(int i=0;i<message.length();i++) {
			if(k==key.length())k=0;
			int asciiValue=((int)message.charAt(i)-(int)key.charAt(k))%256;
			resultMessage+=(char)(asciiValue);
			k++;
		}
		
		
		HashMap<String,String> hm=new HashMap<>();
		hm.put("message", resultMessage);
		return hm;
	}

}
