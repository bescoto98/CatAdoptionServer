package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Cats;

public interface ICatsDAO extends JpaRepository<Cats, Integer> {

}
