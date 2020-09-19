package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.User;

public interface IUserDAO extends JpaRepository<User, Integer> {

	User findByUsername(String username);
}
