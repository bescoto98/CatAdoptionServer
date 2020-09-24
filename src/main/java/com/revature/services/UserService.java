package com.revature.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.models.*;
import com.revature.repositories.*;

@Service
public class UserService {
	
	private IUserDAO udao;
	
	@Autowired
	public UserService(IUserDAO udao) {
		super();
		this.udao = udao;
	}
	
	public User findById(int id) {
		Optional<User> u = udao.findById(id);
		
		if(u.isPresent()) {
			return u.get();
		}
		else {
			return null;
		}
		
	}
	
	public User findByUsername(String username){
		User u = udao.findByUsername(username);
		return u;
	}
	
	public boolean addUser(User u) {
		udao.save(u);
		return true;
	}
	
	public boolean updateUser(User u) {
		udao.save(u);
		return true;
	}
	
	

}
