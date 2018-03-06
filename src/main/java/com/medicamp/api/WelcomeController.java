package com.medicamp.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicamp.sec.RolesService;

@RestController
@RequestMapping("/")
public class WelcomeController {
	
	
	@GetMapping()
	public String welcome() {
		
		return "Welcome to the medicamp API!";
	}

}
