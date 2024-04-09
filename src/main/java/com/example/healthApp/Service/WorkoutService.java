package com.example.healthApp.Service;

import java.util.List;

import com.example.healthApp.model.Workout;


public interface WorkoutService {
	

    public Workout logWorkout(Long userId,Workout workout) throws UserNotFoundException;

    public List<Workout> getWorkoutsByUserId(Long userId) throws UserNotFoundException ;
    
    public String deleteWorkout(Long workoutId);
}
