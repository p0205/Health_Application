package com.example.healthApp.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.healthApp.model.Workout;
import com.example.healthApp.repo.WorkoutRepo;
import com.example.healthApp.model.User;

@Service
public class WorkoutServiceImpl implements WorkoutService{
	
	@Autowired
	private WorkoutRepo workoutRepo;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Override
	public Workout logWorkout(Long userId, Workout workout) throws UserNotFoundException {
		User user = userService.getUserById(userId);
		if(user!=null)
		{
			workout.setUser(user);
			return workoutRepo.save(workout);
		}
		else
		{
			throw new UserNotFoundException();
		}
	}


	@Override
	public List<Workout> getWorkoutsByUserId(Long userId) throws UserNotFoundException {
		User user = userService.getUserById(userId);
		List<Workout> workouts = new ArrayList<>();
		if(user!=null)
		{
			workouts = workoutRepo.findByUser(user);
			return workouts;
		}
		else
		{
			throw new UserNotFoundException();
		}
		
	}


	@Override
	public String deleteWorkout(Long workoutId) {
		try
		{
			workoutRepo.deleteById(workoutId);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "Delete successfully!";
	}

}
