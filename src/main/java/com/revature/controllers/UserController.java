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
	
	private IUserDAO udao;
	private ITasksDAO tdao;
	private ICatsDAO cdao;
	private MagicBox magic;
	
	@Autowired
	public UserController(IUserDAO udao, ITasksDAO tdao, ICatsDAO cdao, MagicBox magic) {
		super();
		this.udao = udao;
		this.tdao = tdao;
		this.cdao = cdao;
		this.magic = magic;
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<User> getUser(HttpSession session, @PathVariable("id") int id){
		
//		if(session.getAttribute("loggedin") == null) {
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//		}
		
		Optional<User> temp = udao.findById(id);
		if(temp.isPresent()) {
			User u = temp.get();
			return ResponseEntity.status(HttpStatus.OK).body(u);
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody User u){
		
		// encrypting password when a new user is created
		PasswordEncoder e = magic.getEncoder();
		String hashed = e.encode(u.getPassword());
		u.setPassword(hashed);
		
		udao.save(u);
		return ResponseEntity.status(HttpStatus.OK).body(udao.findByUsername(u.getUsername()));
	}
	
	@PutMapping
	public ResponseEntity<Optional<User>> updateUser(@RequestBody User u){
		udao.save(u);
		return ResponseEntity.status(HttpStatus.OK).body(udao.findById(u.getUserid()));
	}
	
	
	
	

}
