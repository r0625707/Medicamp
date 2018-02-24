package com.medicamp.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicamp.model.Kind;
import com.medicamp.model.Tak;
import com.medicamp.model.User;
import com.medicamp.model.Voogd;

import com.medicamp.db.UserRepository;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserRepository users;

	@GetMapping()
	public List<User> getAllUsers() {
		return users.findAll();
	}
	
	@GetMapping("/{login}/kind")
	public ResponseEntity<List<Kind>> getAllKinderen(@PathVariable(value = "login") String string) {
		User user = users.findOne(string);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user.getKinderen());
	}
	
	@GetMapping("/{login}/voogd")
	public ResponseEntity<List<Voogd>> getAllVoogden(@PathVariable(value = "login") String string) {
		User user = users.findOne(string);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user.getVoogden());
	}
	@GetMapping("/{login}/tak")
	public ResponseEntity<List<Tak>> getAlltakken(@PathVariable(value = "login") String string) {
		User user = users.findOne(string);
		
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user.getTakken());
	}


	@PostMapping()
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User find = users.findOne(user.getLogin());
		if(find != null) {
			return ResponseEntity.status(500).build();
		}
		String password = user.getPassword();
		user.setPasswordHashed(password);
		users.save(user);
		return ResponseEntity.ok().body(user);
	}

	@GetMapping("/{login}/")
	public ResponseEntity<User> getUser(@PathVariable(value = "login") String string) {
		User user = users.findOne(string);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}

	@PutMapping("/{login}/")
	public ResponseEntity<User> updateNote(@PathVariable(value = "login") String string, @RequestBody User user) {
	
		User oldUser = users.findOne(string);

		if (oldUser == null) {
			return ResponseEntity.notFound().build();
		}
				
//		oldUser.setLogin(user.getLogin());
		oldUser.setNaam(user.getNaam());
		oldUser.setRole(user.getRole());
		oldUser.setTel(user.getTel());
		oldUser.setVoornaam(user.getVoornaam());
				
		User updatedUser = users.save(oldUser);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{login}/")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "login") String string) {
		User note = users.findOne(string);
		if (note == null) {
			return ResponseEntity.notFound().build();
		}

		note.getVoogden().removeAll(note.getVoogden());
		note.getKinderen().removeAll(note.getKinderen());
		note.getGroepen().removeAll(note.getGroepen());
		users.delete(note);
		return ResponseEntity.ok().build();
	}

}
