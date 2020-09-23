package com.revature.controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.revature.models.*;
import com.revature.repositories.*;
import com.revature.services.*;

@RestController
@RequestMapping(value="/user")
@CrossOrigin
@ResponseBody
public class UserController {
	
	private EncryptionService es;
	private UserService us;
	
	@Autowired
	public UserController(EncryptionService es, UserService us) {
		super();
		this.es = es;
		this.us = us;
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<User> getUser(HttpSession session, @PathVariable("id") int id){
		
//		if(session.getAttribute("loggedin") == null) {
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//		}
		
		User temp = us.findById(id);
		if(temp != null) {
			return ResponseEntity.status(HttpStatus.OK).body(temp);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}


	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User u){
		
		// encrypting password when a new user is created
		PasswordEncoder e = es.getEncoder();
		String hashed = e.encode(u.getPassword());
		u.setPassword(hashed);
		
		us.addUser(u);
		return ResponseEntity.status(HttpStatus.OK).body(us.findByUsername(u.getUsername()));
	}
	
	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User u){
		us.updateUser(u);
		return ResponseEntity.status(HttpStatus.OK).body(us.findById(u.getUserid()));
	}
	
	
	
	

}
