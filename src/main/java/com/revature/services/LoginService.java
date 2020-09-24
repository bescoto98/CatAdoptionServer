package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.models.User;

@Service
public class LoginService {
	
	private UserService us;
	private EncryptionService es;
	
	@Autowired
	public LoginService(UserService us, EncryptionService es) {
		super();
		this.us = us;
		this.es = es;
	}
	
	public boolean authenticate(String username, String password) {
		User temp = us.findByUsername(username);
		
		if(temp != null) {
			PasswordEncoder e = es.getEncoder();
			
			if(e.matches(password, temp.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	

}
