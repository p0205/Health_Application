package com.example.healthApp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.healthApp.model.User;
import com.example.healthApp.repo.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	@Qualifier("pwencoder")
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User createUser(User user) {
		User tempUser = User.builder()
				.id(user.getId())
				.name(user.getName())
				.age(user.getAge())
				.height(user.getHeight())
				.weight(user.getWeight())
				.role(user.getRole())
				.username(user.getUsername())
				.password(user.getPassword())
				.build();
		return userRepo.save(tempUser);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User getUserById(Long id) {
	  Optional<User> optionalUser = userRepo.findById(id);
	    return optionalUser.orElse(null); //
		
	}

	@Override
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	@Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

}
