package com.barath.restgoals.user;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity(name = "user_details")
public class User {
	@Id
	@GeneratedValue
	private int id;
	@Size(min = 2, message = "Name should be minimum of 2 characters")
	private String name;
	@Past(message="Birth Date cannot be a future date0")
	private LocalDate dateOfBirth;
	@OneToMany(mappedBy = "user")
	private List<Post> post;
	public User(int id, String name, LocalDate dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}
	
	public User() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public String toString() {
		return String.format("User [id=%s, name=%s, dateOfBirth=%s]", id, name, dateOfBirth);
	}
	
	
}
