package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.*;
import com.revature.repositories.*;


@RestController
@RequestMapping(value="/login")
@CrossOrigin
public class LoginController {
	
	private IUserDAO udao;

	@Autowired
	public LoginController(IUserDAO udao) {
		super();
		this.udao = udao;
	}
	
	@PostMapping
	public ResponseEntity<User> authenticate(@RequestBody UserDTO u){
		User temp = udao.findByUsername(u.username);
		
		
		if(temp.getPassword().equals(u.password)) {
			return ResponseEntity.status(HttpStatus.OK).body(temp);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		 
	}
	
	

}
