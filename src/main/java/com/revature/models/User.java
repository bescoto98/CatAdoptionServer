package com.revature.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity 
@Component 
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userid;
	private String firstName;
	private String lastName;
	private int age;
	private String username;
	private String password;
	private String profileImage;
	
	//list of cats that the user adopts
	@JsonManagedReference
	@OneToMany(mappedBy="owner", fetch=FetchType.EAGER)
	private List<Cats> cats;
	
	//list of tasks that the user has
	@JsonManagedReference
	@OneToMany(mappedBy="doer", fetch=FetchType.LAZY)
	private List<Tasks> tasks;
	//tracks the users' points based on login frequency and task completion
	//points allow users to adopt cats (currency)
	private int points;
	
	public User() {
		super();
	}

	public User(int userid, String firstName, String lastName, int age, String username, String password,
			String profileImage, List<Cats> cats, List<Tasks> tasks, int points) {
		super();
		this.userid = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.username = username;
		this.password = password;
		this.profileImage = profileImage;
		this.cats = cats;
		this.tasks = tasks;
		this.points = points;
	}


	public User(String firstName, String lastName, int age, String username, String password, String profileImage,
			List<Cats> cats, List<Tasks> tasks, int points) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.username = username;
		this.password = password;
		this.profileImage = profileImage;
		this.cats = cats;
		this.tasks = tasks;
		this.points = points;
	}


	public User(int userid, String firstName, String lastName, int age, String username, String password, int points) {
		super();
		this.userid = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.username = username;
		this.password = password;
		this.points = points;
	}
	
	public User(int userid) {
		super();
		this.userid = userid;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getProfileImage() {
		return profileImage;
	}


	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}


	public List<Cats> getCats() {
		return cats;
	}


	public void setCats(List<Cats> cats) {
		this.cats = cats;
	}


	public List<Tasks> getTasks() {
		return tasks;
	}


	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}


	public int getPoints() {
		return points;
	}


	public void setPoints(int points) {
		this.points = points;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((cats == null) ? 0 : cats.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + points;
		result = prime * result + ((profileImage == null) ? 0 : profileImage.hashCode());
		result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
		result = prime * result + userid;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (age != other.age)
			return false;
		if (cats == null) {
			if (other.cats != null)
				return false;
		} else if (!cats.equals(other.cats))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (points != other.points)
			return false;
		if (profileImage == null) {
			if (other.profileImage != null)
				return false;
		} else if (!profileImage.equals(other.profileImage))
			return false;
		if (tasks == null) {
			if (other.tasks != null)
				return false;
		} else if (!tasks.equals(other.tasks))
			return false;
		if (userid != other.userid)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [userid=" + userid + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
				+ ", username=" + username + ", password=" + password + ", profileImage=" + profileImage + ", cats="
				+ cats + ", tasks=" + tasks + ", points=" + points + "]";
	}
	
	
}
