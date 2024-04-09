package com.example.healthApp.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.healthApp.model.FitnessGoal;
import com.example.healthApp.model.User;
import com.example.healthApp.repo.FitnessGoalRepo;

@Service
public class FitnessServiceImpl implements FitnessService {
    
    @Autowired
    private FitnessGoalRepo fitnessGoalRepo;
    
    @Autowired
    private UserServiceImpl userService;

    @Override
    public FitnessGoal createGoal(FitnessGoal goal) {
        // Save the goal and return the saved instance
        return fitnessGoalRepo.save(goal);
    }

	@Override
	public List<FitnessGoal> getGoalsByUserId(Long userId) {
		User user = userService.getUserById(userId);
		List<FitnessGoal> goals = new ArrayList<>();
		if(user == null)
		{
			System.out.println("User is not exist");
		}
		else
		{
			goals = fitnessGoalRepo.findGoalsByUser(user);
		}
		return goals;
	}

	@Override
	public String deleteGoal(Long goalId) {
		try {
			fitnessGoalRepo.deleteById(goalId);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "Delete successfully!";
	}


}


