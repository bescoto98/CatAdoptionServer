package com.revature.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.revature.models.*;
import com.revature.repositories.*;
import com.revature.services.*;


@RestController
@RequestMapping(value="/login")
@CrossOrigin
public class LoginController {
	
	private LoginService ls;
	private UserService us;
	
	@Autowired
	public LoginController(LoginService ls, UserService us) {
		super();
		this.ls = ls;
		this.us = us;

	}
	
	@PostMapping
	public ResponseEntity<User> authenticate(HttpSession session, @RequestBody UserDTO u){
		
		if(ls.authenticate(u.username, u.password)) {
			return ResponseEntity.status(HttpStatus.OK).body(us.findByUsername(u.username));
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		 
	}
	
	

}
