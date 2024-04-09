package com.example.healthApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.healthApp.model.FitnessGoal;
import com.example.healthApp.model.User;

@Repository
public interface FitnessGoalRepo extends JpaRepository<FitnessGoal,Long>{
	@Query("SELECT g FROM FitnessGoal g WHERE g.user = :user")
    List<FitnessGoal> findGoalsByUser(@Param("user") User user);
	
}
