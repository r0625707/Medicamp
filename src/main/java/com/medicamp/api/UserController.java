package com.medicamp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.medicamp.model.User;
import com.medicamp.model.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
@Autowired
UserRepository users;
	
	@GetMapping()
	public List<User> getAllUsers() {
	    return users.findAll();
	}
	
	@PostMapping()
	public User createUser(@RequestBody User user) {
		
		String pass = "SECURITY NOT IMPLEMENTED!";
		String salt = "SECURITY NOT IMPLEMENTED!";
		
		user.setPassword(pass);
		user.setSalt(salt);
		return users.save(user);
		
	}

}
