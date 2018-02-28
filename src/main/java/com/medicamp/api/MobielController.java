package com.medicamp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicamp.mobiel.ApiFixer;

@RestController
@RequestMapping("api")
public class MobielController {
	
	@Autowired
	ApiFixer f;
	
	
	@GetMapping("/user/{login}/mobiel")
	public /*ResponseEntity<List<Tak>>*/List<Object> mobiele(@PathVariable(value = "login") String string) {
		return f.fix(string);
		
	/*	User user = users.findOne(string);
		
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user.getTakken());*/
	}
	

}
