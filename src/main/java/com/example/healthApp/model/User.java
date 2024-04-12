package com.example.healthApp.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	
}
