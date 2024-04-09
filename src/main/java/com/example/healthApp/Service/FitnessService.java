package com.example.healthApp.Service;

import java.util.List;


import com.example.healthApp.model.FitnessGoal;

public interface FitnessService {
	
	public FitnessGoal createGoal(FitnessGoal goal);
	public List<FitnessGoal> getGoalsByUserId(Long userId);
	public String deleteGoal(Long goalId);


}
