package com.revature.services;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class EncryptionService {
	
	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10,new SecureRandom());
	
	public EncryptionService() {
		super();
	}
	
	public PasswordEncoder getEncoder() {
		return encoder;
	}

}
