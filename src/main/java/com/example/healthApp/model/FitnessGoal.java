package com.example.healthApp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class FitnessGoal {

	
	public FitnessGoal() {}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String description;
	private double targetWeight;
	private LocalDate targetDate;

	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
//	@JoinColumn(name="user_id", nullable = false)
	private User user;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getTargetWeight() {
		return targetWeight;
	}
	public void setTargetWeight(double targetWeight) {
		this.targetWeight = targetWeight;
	}
	public LocalDate getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
	@Override
	public String toString() {
		return "FitnessGoal [id=" + id + ", description=" + description + ", targetWeight=" + targetWeight
				+ ", targetDate=" + targetDate + ", user=" + user + "]";
	}
}
