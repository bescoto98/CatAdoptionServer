package com.revature.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.*;
import com.revature.services.*;

/**
 * 
 * LoginController is used to handle incoming login and logout requests
 *	
 *	POST - /login	- authenticates user credentials
 *	GET  - /login	- invalidates session
 *
 */


@RestController
@RequestMapping(value="/login")
@CrossOrigin
public class LoginController {
	
	private LoginService ls;
	private UserService us;
	private HttpSession session;
	
	@Autowired
	public LoginController(LoginService ls, UserService us, HttpSession session) {
		super();
		this.ls = ls;
		this.us = us;
		this.session = session;

	}
	
	
	@PostMapping
	public ResponseEntity<User> authenticate(@RequestBody UserDTO u){
		
		if(ls.authenticate(u.username, u.password)) {
			session.setAttribute("loggedin", true);
			return ResponseEntity.status(HttpStatus.OK).body(us.findByUsername(u.username));
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		 
	}
	
	@GetMapping
	public ResponseEntity<Boolean> logout(){
		session.setAttribute("loggedin", false);
		session.invalidate();
		return ResponseEntity.status(HttpStatus.OK).body(true);
		
	}
	

}
