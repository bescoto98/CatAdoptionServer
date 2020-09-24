package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Component
@Table(name="cats")
public class Cats {
	
	@Id
	private int catid;
	//use point price to check against user points
	private int pointPrice;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="userid")
	@JsonBackReference
	private User owner;
	
	private boolean adoptionStatus;

	public Cats() {
		super();
	}

	// without int id, with User owner
	public Cats(int pointPrice, User owner, boolean adoptionStatus) {
		super();
		this.pointPrice = pointPrice;
		this.owner = owner;
		this.adoptionStatus = adoptionStatus;
	}

	// with int id, without User owner
	public Cats(int catid, int pointPrice, boolean adoptionStatus) {
		super();
		this.catid = catid;
		this.pointPrice = pointPrice;
		this.adoptionStatus = adoptionStatus;
	}
	
	//without int id and User owner
	public Cats(int pointPrice, boolean adoptionStatus) {
		super();
		this.pointPrice = pointPrice;
		this.adoptionStatus = adoptionStatus;
	}

	//with all arguments
	public Cats(int catid, int pointPrice, User owner, boolean adoptionStatus) {
		super();
		this.catid = catid;
		this.pointPrice = pointPrice;
		this.owner = owner;
		this.adoptionStatus = adoptionStatus;
	}

	public int getCatid() {
		return catid;
	}

	public void setCatid(int catid) {
		this.catid = catid;
	}

	public int getPointPrice() {
		return pointPrice;
	}

	public void setPointPrice(int pointPrice) {
		this.pointPrice = pointPrice;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean isAdoptionStatus() {
		return adoptionStatus;
	}

	public void setAdoptionStatus(boolean adoptionStatus) {
		this.adoptionStatus = adoptionStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (adoptionStatus ? 1231 : 1237);
		result = prime * result + catid;
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime * result + pointPrice;
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
		Cats other = (Cats) obj;
		if (adoptionStatus != other.adoptionStatus)
			return false;
		if (catid != other.catid)
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (pointPrice != other.pointPrice)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cats [catid=" + catid + ", pointPrice=" + pointPrice + ", owner=" + owner.getUserid() + ", adoptionStatus="
				+ adoptionStatus + "]";
	}
	
}
