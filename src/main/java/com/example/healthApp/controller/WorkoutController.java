package com.example.healthApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthApp.Service.UserNotFoundException;
import com.example.healthApp.Service.WorkoutServiceImpl;
import com.example.healthApp.model.Workout;

@RestController
@RequestMapping("/healthApp/workout")
public class WorkoutController {
	
	@Autowired
	private WorkoutServiceImpl workoutService;
	
	@PostMapping("/log/{userId}")
	public ResponseEntity<Workout> createWorkout(@PathVariable(value="userId") Long userId, @RequestBody Workout workout)
	{
		try {
			Workout loggedWorkout = workoutService.logWorkout(userId,workout);
			return new ResponseEntity<>(loggedWorkout,HttpStatus.OK);
		}catch(UserNotFoundException e)
		{
			e.printUserNotFound(userId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<Workout>> getWorkoutsByUserId(@PathVariable(value="userId") Long userId)
	{
		try {
			List<Workout> workouts = workoutService.getWorkoutsByUserId(userId);
			if(workouts.isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else
			{
				return new ResponseEntity<>(workouts,HttpStatus.OK);
			}
		}
		catch(UserNotFoundException e)
		{
			e.printUserNotFound(userId);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{workoutId}")
	public String deleteWorkout(@PathVariable(value="workoutId") Long workoutId)
	{
		return workoutService.deleteWorkout(workoutId);
	}


}
