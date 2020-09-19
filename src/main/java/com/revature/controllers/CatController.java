package com.revature.controllers;

import java.util.List;
import java.util.Optional;

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

import com.revature.models.*;
import com.revature.repositories.*;


@RestController
@RequestMapping(value="/cats")
@CrossOrigin
@ResponseBody
public class CatController {
	
	private IUserDAO udao;
	private ITasksDAO tdao;
	private ICatsDAO cdao;
	
	@Autowired
	public CatController(IUserDAO udao, ITasksDAO tdao, ICatsDAO cdao) {
		super();
		this.udao = udao;
		this.tdao = tdao;
		this.cdao = cdao;
	}
	
	@GetMapping(value="/adopted")
	public ResponseEntity<List<Cats>> getAdoptedCats(){
		return ResponseEntity.status(HttpStatus.OK).body(cdao.findAll());
	}
	
	@PutMapping
	public ResponseEntity<Optional<Cats>> updateCat(@RequestBody Cats c){
		cdao.save(c);
		return ResponseEntity.status(HttpStatus.OK).body(cdao.findById(c.getCatid()));
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<List<Cats>> getUsersCats(@PathVariable("id")int id){
		return ResponseEntity.status(HttpStatus.OK).body(cdao.findByOwner(id));
	}
	
	
}
