package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

/*
 * Task Class: 
 *  - defines fields for tasks created by the user that are persisted
 *  to database;
 *  
 *  -task are equivalent to to-do list items
 */
@Component
@Entity
@Table(name="tasks")
public class Tasks {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int taskid;
	public String description;
	public boolean completionStatus;
	//this field tracks the users' frequency of using a certain task
	//frequency of use
	public int frequency;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="userid")
	private User doer;
	//add many to one relationship with users
	
	public Tasks() {
		super();
		// TODO Auto-generated constructor stub
	}

	// without int id, with User doer
	public Tasks(String description, boolean completionStatus, int frequency, User doer) {
		super();
		this.description = description;
		this.completionStatus = completionStatus;
		this.frequency = frequency;
		this.doer = doer;
	}

	// with int taskid, without User doer
	public Tasks(int taskid, String description, boolean completionStatus, int frequency) {
		super();
		this.taskid = taskid;
		this.description = description;
		this.completionStatus = completionStatus;
		this.frequency = frequency;
	}

	// with both int id AND User owner
	public Tasks(int taskid, String description, boolean completionStatus, int frequency, User doer) {
		super();
		this.taskid = taskid;
		this.description = description;
		this.completionStatus = completionStatus;
		this.frequency = frequency;
		this.doer = doer;
	}
	
	// with description, completion status, and frequency
	public Tasks(String description, boolean completionStatus, int frequency) {
		super();
		this.description = description;
		this.completionStatus = completionStatus;
		this.frequency = frequency;
	}
	
	
	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(boolean completionStatus) {
		this.completionStatus = completionStatus;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public User getDoer() {
		return doer;
	}

	public void setDoer(User doer) {
		this.doer = doer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (completionStatus ? 1231 : 1237);
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((doer == null) ? 0 : doer.hashCode());
		result = prime * result + frequency;
		result = prime * result + taskid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tasks other = (Tasks) obj;
		if (completionStatus != other.completionStatus)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (doer == null) {
			if (other.doer != null)
				return false;
		} else if (!doer.equals(other.doer))
			return false;
		if (frequency != other.frequency)
			return false;
		if (taskid != other.taskid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tasks [taskid=" + taskid + ", description=" + description + ", completionStatus=" + completionStatus
				+ ", frequency=" + frequency + ", doer=" + doer + "]";
	}
	
	
}

