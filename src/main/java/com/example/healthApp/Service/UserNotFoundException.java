package com.example.healthApp.Service;

public class UserNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	UserNotFoundException()
	{
		super();
	}
	
	public String printUserNotFound(Long id)
	{
		return "User with id " + id +" is not found";
	}
}
