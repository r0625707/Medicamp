package com.medicamp.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicamp.db.UserRepository;
import com.medicamp.model.User;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/auth/")
public class AuthController {
	
	
	@Autowired
	UserRepository users;
	
	@Autowired
	PasswordEncoderImpl passwordEncoder;
	

	@PostMapping("register")
	public ResponseEntity<String> register(@RequestBody User user) {
	
		User find = users.findOne(user.getLogin());
		if(find != null) {
			return ResponseEntity.status(500).build();
		}
		String password = user.getPassword();
		user.setPassword(passwordEncoder.encode(password));
		users.save(user);
		return ResponseEntity.ok().body("");
	}

	
	    
	
	
	
}
