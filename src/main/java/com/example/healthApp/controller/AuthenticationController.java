package com.example.healthApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.healthApp.Service.JwtService;
import com.example.healthApp.model.ReqBody;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("/healthApp")
public class AuthenticationController {

	@Autowired
	private JwtService jwtSerivce;
	
	@Autowired
	private AuthenticationManager authenticatonManager;
	
	@RequestMapping("/userDashboard")
	@ResponseBody
	public String userPage()
	{
		return "Welcome User!";
	}


	
	@RequestMapping("/adminDashboard")
	@ResponseBody
	public String adminPage()
	{
		return "Start working admin!";
	}
	
	@PostMapping("/login")
	public String login(@RequestBody ReqBody req)
	{

		String providedUsername = req.getUsername();
		String providedPassword = req.getPassword();
		Authentication token = new UsernamePasswordAuthenticationToken(providedUsername,providedPassword);
			
		//1. pass the unauthenticated token to authentication manager
		//2. authentication manager check if it authenticated already
		//3. if not, manager pass the request to provider for authentication
		Authentication auth = authenticatonManager.authenticate(token);
	
		
		if(auth.isAuthenticated())
		{

			return jwtSerivce.createToken(providedUsername);
		}
		else
		{
			System.out.println("Invalid user request!");
			throw new UsernameNotFoundException("invalid user request !"); 
		}
	}

	@RequestMapping("/logout")
	@ResponseBody
	public String logout()
	{
		return "Thanks for using!";
	}
	
	@RequestMapping("/deniedAccess")
	@ResponseBody
	public String deniedAccess()
	{
		return "Sorry! You don't have authority to access!";
	}
	
	@RequestMapping("/failedAuth")
	@ResponseBody
	public String failedAuth()
	{
		return "Wrong username or password";
	}

	
}
