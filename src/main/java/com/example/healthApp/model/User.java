package com.example.healthApp.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private int age;
	private String username;
	private String password;
	private double weight;
	private double height;
	private String role;
	
	 @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Workout> workouts;
	 
	 @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	 private List<FitnessGoal> goals;
	 
	public List<Workout> getWorkouts() {
		return workouts;
	}

	public void setWorkouts(List<Workout> workouts) {
		this.workouts = workouts;
	}

	public List<FitnessGoal> getGoals() {
		return goals;
	}

	public void setGoals(List<FitnessGoal> goals) {
		this.goals = goals;
	}

	
	
	public User() {}
	
	public User(Long id, String name, int age, String username, String password, double weight, double height,
			String role) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.username = username;
		this.password = password;
		this.weight = weight;
		this.height = height;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	

}
