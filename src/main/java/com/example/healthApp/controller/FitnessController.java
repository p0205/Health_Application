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

import com.example.healthApp.Service.FitnessServiceImpl;
import com.example.healthApp.Service.UserServiceImpl;
import com.example.healthApp.model.FitnessGoal;
import com.example.healthApp.model.User;

@RestController
@RequestMapping("/healthApp/fitness")
public class FitnessController {
	
	@Autowired
	FitnessServiceImpl fitnessService;
	@Autowired
	UserServiceImpl userService;
	
	@GetMapping("/hello")
	public String test()
	{
		return "Fitness!";
	}
	
	@PostMapping("/createGoal/{user_id}/")
	public ResponseEntity<String> createFitnessGoal(@PathVariable(value="user_id") Long userId, @RequestBody FitnessGoal goal)
	{
		User user = userService.getUserById(userId);
		try {
			if(user.equals(null))
			{
				 System.out.println("The user does not exist.");
//				 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				 return ResponseEntity.notFound().build();
			}
			else
			{
				goal.setUser(user);
//				return new ResponseEntity<>(fitnessService.createGoal(goal),HttpStatus.OK);
				fitnessService.createGoal(goal);
				return ResponseEntity.ok("New goal is created!");
			}		
		}catch(Exception e)
		{
			e.printStackTrace();
// 	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
 	        return ResponseEntity.internalServerError().build();
		}
		
	}
	
	@GetMapping("/getGoals/{id}")
	public ResponseEntity<List<FitnessGoal>> getFitnessGoal(@PathVariable Long id)
	{
		
			List<FitnessGoal> fitnessGoal = fitnessService.getGoalsByUserId(id);
			if(fitnessGoal.isEmpty())
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(fitnessGoal, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{goalId}")
	public String deleteWorkout(@PathVariable(value="goalId") Long goalId)
	{
		return fitnessService.deleteGoal(goalId);
	}


}
