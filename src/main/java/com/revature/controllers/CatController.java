package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.*;
import com.revature.services.*;

/**
 * 
 * CatController is used to handle incoming requests involving cats
 * mapped to:
 * 		- /catadoption/cats/
 * 
 * GET 	- /adopted 	- returns all adopted cats
 * PUT  - / 		- adds a cat to the cats table
 * GET 	- /{id} 	- returns a specific user's cat based on the userid
 *
 */


@RestController
@RequestMapping(value="/cats")
@CrossOrigin
@ResponseBody
public class CatController {
	
	private CatService cs;
	private UserService us;
	private HttpSession session;
	
	@Autowired
	public CatController(CatService cs, UserService us, HttpSession session) {
		super();
		this.cs = cs;
		this.us = us;
		this.session = session;
	}

	
	@GetMapping(value="/adopted")
	public ResponseEntity<List<Cats>> getAdoptedCats(){
		
		if(session.getAttribute("loggedin") == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(cs.findAll());
	}
	
	@PutMapping
	public ResponseEntity<Cats> updateCat(@RequestBody Cats c){
		
		if(session.getAttribute("loggedin") == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		User tempUser = us.findById(c.getOwner().getUserid());
		
		if(tempUser != null) {
			c.setOwner(tempUser);
			cs.addCat(c);
			return ResponseEntity.status(HttpStatus.OK).body(cs.findById(c.getCatid()));
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<List<Cats>> getUsersCats(@PathVariable("id")int id){
		
		if(session.getAttribute("loggedin") == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
		
		User u = us.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(cs.findByOwner(u)); 
		
		
	}
	
	
}
