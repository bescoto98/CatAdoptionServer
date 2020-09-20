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
@RequestMapping(value="/task")
@CrossOrigin
@ResponseBody
public class TaskController {
	
	private IUserDAO udao;
	private ITasksDAO tdao;

	@Autowired
	public TaskController(IUserDAO udao, ITasksDAO tdao) {
		super();
		this.udao = udao;
		this.tdao = tdao;
	}
	
	@PostMapping
	public ResponseEntity<List<Tasks>> addTask(@RequestBody Tasks t){
		Optional<User> tempUser = udao.findById(t.getDoer().getUserid());
		
		if(tempUser.isPresent()) {
			t.setDoer(tempUser.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		tdao.save(t);
		
		return ResponseEntity.status(HttpStatus.OK).body(tdao.findByDoer(t.getDoer()));
	}
	
	@PutMapping
	public ResponseEntity<List<Tasks>> updateTask(@RequestBody Tasks t){
		Optional<User> tempUser = udao.findById(t.getDoer().getUserid());
		
		if(tempUser.isPresent()) {
			t.setDoer(tempUser.get());
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		tdao.save(t);
		
		return ResponseEntity.status(HttpStatus.OK).body(tdao.findByDoer(t.getDoer()));
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<List<Tasks>> getUserTasks(@PathVariable("id")int id){
		Optional<User> temp = udao.findById(id);
		
		if(temp.isPresent()) {
			User u = temp.get();
			return ResponseEntity.status(HttpStatus.OK).body(tdao.findByDoer(u));
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@GetMapping(value="/frequency/{val}")
	public ResponseEntity<List<Tasks>> getByFrequency(@PathVariable("val")int val){
		return ResponseEntity.status(HttpStatus.OK).body(tdao.findByFrequencyGreaterThan(val));
	}
	
	
	

}
