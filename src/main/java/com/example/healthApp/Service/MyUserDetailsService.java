package com.example.healthApp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.healthApp.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.healthApp.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//retrieve data
		User user = userRepo.findByUsername(username);
	
		if(user.equals(null))
		{
			 throw new UsernameNotFoundException("User : ["+username + "] is not found!");
		}
		//build a userDetails from the data retrieved
		UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
				.username(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRole())
				.build();
		return userDetails;
	}
}
