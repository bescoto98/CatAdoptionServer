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
import com.revature.services.*;

@RestController
@RequestMapping(value="/task")
@CrossOrigin
@ResponseBody
public class TaskController {
	
	private UserService us;
	private TaskService ts;
	
	
	@Autowired
	public TaskController(UserService us, TaskService ts) {
		super();
		this.us = us;
		this.ts = ts;
	}
	
	@PostMapping
	public ResponseEntity<List<Tasks>> addTask(@RequestBody Tasks t){
		User tempUser = us.findById(t.getDoer().getUserid());
		
		if(tempUser != null) {
			t.setDoer(tempUser);
			ts.addTask(t);
			return ResponseEntity.status(HttpStatus.OK).body(ts.findUserTasks(tempUser));
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@PutMapping
	public ResponseEntity<List<Tasks>> updateTask(@RequestBody Tasks t){
		User tempUser = us.findById(t.getDoer().getUserid());
		
		if(tempUser != null) {
			t.setDoer(tempUser);
			ts.addTask(t);
			return ResponseEntity.status(HttpStatus.OK).body(ts.findUserTasks(tempUser));
		}
		else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<List<Tasks>> getUserTasks(@PathVariable("id")int id){
		
		User tempUser = us.findById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(ts.findUserTasks(tempUser));
	}
	
	@GetMapping(value="/frequency/{val}")
	public ResponseEntity<List<Tasks>> getByFrequency(@PathVariable("val")int val){
		return ResponseEntity.status(HttpStatus.OK).body(ts.findByFrequency(val));
	}
	
	
	

}
