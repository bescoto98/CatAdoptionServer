package com.revature.controllers;

import java.util.List;

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
	
	@Autowired
	public CatController(CatService cs, UserService us) {
		super();
		this.cs = cs;
		this.us = us;
	}

	
	@GetMapping(value="/adopted")
	public ResponseEntity<List<Cats>> getAdoptedCats(){
		
		return ResponseEntity.status(HttpStatus.OK).body(cs.findAll());
	}
	
	@PutMapping
	public ResponseEntity<Cats> updateCat(@RequestBody Cats c){
		
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
		
		User u = us.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(cs.findByOwner(u)); 
		
		
	}
	
	
}
