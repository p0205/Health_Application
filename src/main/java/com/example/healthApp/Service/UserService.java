package com.example.healthApp.Service;

import java.util.List;

import com.example.healthApp.model.User;

public interface UserService {
	
	public User createUser(User user);
	public List<User> getAllUsers();
	public User getUserById(Long id);
	public User updateUser(User user);
	public void deleteUser(Long id);

}
