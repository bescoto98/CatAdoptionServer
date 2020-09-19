package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Cats;

public interface ICatsDAO extends JpaRepository<Cats, Integer> {
	
	List<Cats> findByOwner(int userid);

}
