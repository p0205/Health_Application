package com.example.healthApp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.healthApp.model.User;
import com.example.healthApp.model.Workout;

@Repository
public interface WorkoutRepo extends JpaRepository<Workout,Long>{
	@Query("select w from Workout w where w.user = :user")
	 List<Workout> findByUser(@Param("user") User user);
}
