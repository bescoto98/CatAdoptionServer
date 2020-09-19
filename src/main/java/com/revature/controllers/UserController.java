package com.revature.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.*;
import com.revature.repositories.*;

@RestController
@RequestMapping(value="/user")
@CrossOrigin
@ResponseBody
public class UserController {
	
	private IUserDAO udao;
	private ITasksDAO tdao;
	private ICatsDAO cdao;
	
	@Autowired
	public UserController(IUserDAO udao, ITasksDAO tdao, ICatsDAO cdao) {
		super();
		this.udao = udao;
		this.tdao = tdao;
		this.cdao = cdao;
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id){
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
		udao.save(u);
		return ResponseEntity.status(HttpStatus.OK).body(udao.findByUsername(u.getUsername()));
	}
	
	
	
	

}
