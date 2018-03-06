package com.medicamp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicamp.mobiel.ApiFixer;
import com.medicamp.model.Kind;

@RestController
@CrossOrigin(origins="http://localhost:3000")

@RequestMapping("/api")
public class MobielController {
	
	@Autowired
	ApiFixer f;
	
	
	@GetMapping("/{login}/mobiel")
	public /*ResponseEntity<List<Tak>>*/List<Object> mobiele(@RequestHeader(value="Authorization") String authorization, @PathVariable(value = "login") String string) {

		return f.fix(string,authorization);
		
	/*	User user = users.findOne(string);
		
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user.getTakken());*/
	}
	

}
