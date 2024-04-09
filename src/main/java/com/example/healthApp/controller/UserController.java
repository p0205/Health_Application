package com.example.healthApp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthApp.Service.UserServiceImpl;
import com.example.healthApp.model.User;

@RestController
@RequestMapping("/healthApp/admin/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	
	@GetMapping("/helloworld")
	public String test()
	{
		System.out.println("Reach helloworld endpoints......");
		return "Hello Word";
	}
	


	
	
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

	
	 	@GetMapping("/get/all")
	    public ResponseEntity<List<User>> getAllUsers() {
		 return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	    }
	    

	 	@GetMapping("/get/{id}")
	 	public ResponseEntity<User> getUserById(@PathVariable Long id) {
	 	    try {
	 	        User user = userService.getUserById(id);
	 	        if (user.equals(null)) {
	 	            // Log user retrieval for debugging
	 	            System.out.println("User retrieved: " + user);
	 	            return new ResponseEntity<>(user, HttpStatus.OK);
	 	        } else {
	 	            // Log user not found for debugging
	 	            System.out.println("User not found with ID: " + id);
	 	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 	        }
	 	    } catch (Exception e) {
	 	        // Log exception for debugging
	 	        e.printStackTrace();
	 	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	 	    }
	 	}


	    @PutMapping("/update/{id}")
	    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
	    	User tempUser = new User();
	    	tempUser = user;
	    	tempUser.setId(id);
	        User updatedUser = userService.updateUser(tempUser);
	        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
	        userService.deleteUser(id);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }

}
