package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.models.*;
import com.revature.repositories.*;


@Service
public class CatService {
	private ICatsDAO cdao;
	
	@Autowired
	public CatService(ICatsDAO cdao) {
		super();
		this.cdao = cdao;
	}
	
	public List<Cats> findAll(){
		return cdao.findAll();
	}
	
	public Cats findById(int id) {
		Optional<Cats> c = cdao.findById(id);
		
		if(c.isPresent()) {
			return c.get();
		}
		else {
			return null;
		}
	}
	
	public List<Cats> findByOwner(User u){
		return cdao.findByOwner(u);
		
	}
	
	public boolean addCat(Cats c) {
		
		cdao.save(c);
		
		return true;
	}
	
	
	
	
}
