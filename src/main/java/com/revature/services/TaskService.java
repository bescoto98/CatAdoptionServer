package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.models.*;
import com.revature.repositories.*;

@Service
public class TaskService {

	private ITasksDAO tdao;

	@Autowired
	public TaskService(ITasksDAO tdao) {
		super();
		this.tdao = tdao;
	}
	
	public boolean addTask(Tasks t) {
		
		tdao.save(t);
		
		return true;
	}
	
	public boolean updateTask(Tasks t) {
		tdao.save(t);
		
		return true;
	}
	
	public List<Tasks> findUserTasks(User u){
		
		return tdao.findByDoer(u);
		
	}
	
	public List<Tasks> findByFrequency(int frequency){
		return tdao.findByFrequencyGreaterThan(frequency);
	}
	
}
