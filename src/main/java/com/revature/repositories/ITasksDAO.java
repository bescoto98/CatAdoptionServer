package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Tasks;
import com.revature.models.User;

public interface ITasksDAO extends JpaRepository<Tasks, Integer> {

	List<Tasks> findByDoer(User u);
	List<Tasks> findByFrequencyGreaterThan(int frequency);
}
