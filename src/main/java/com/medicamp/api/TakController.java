package com.medicamp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.medicamp.db.TakRepository;
import com.medicamp.db.UserRepository;
import com.medicamp.model.Activiteit;
import com.medicamp.model.Tak;
import com.medicamp.model.User;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/tak")
public class TakController {

	@Autowired
	TakRepository takken;
	
	@Autowired
	UserRepository users;
	
	@GetMapping("/{idtak}")
	public Tak getTakById(@PathVariable (value="idtak") String idtak) {
		return takken.findOne(idtak);
	}
	
	@PutMapping("/{idtak}")
	public ResponseEntity<Tak> updateTak(@PathVariable (value="idtak") String idtak, @RequestBody Tak tak) {
		Tak oldTak = takken.findOne(idtak);
		if(oldTak == null) {
			return ResponseEntity.notFound().build();
		}
		takken.save(tak);
		return ResponseEntity.ok().body(tak);
	}
	
	@DeleteMapping("/{idtak}")
	public ResponseEntity<Tak> deleteTak(@PathVariable (value="idtak") String idtak) {
		Tak found = takken.findOne(idtak);
		if(found == null) {
			return ResponseEntity.notFound().build();
		}
		takken.delete(idtak);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{idtak}/leiding")
	public List<User> getLeidingFromTak(@PathVariable (value="idtak") String idtak) {
		return takken.findOne(idtak).getUsers();
	}
	
	@PostMapping("/{idtak}/leiding")
	public ResponseEntity<Tak> addLeiderToTak(@PathVariable (value="idtak") String idtak, @RequestBody String login) {
		Tak tak = takken.findOne(idtak);
		User leiding = users.findOne(login);
		if(tak == null) {
			return ResponseEntity.notFound().build();
		} else if (leiding == null) {
			//TODO nieuw account maken voor leider + mail sturen met gegenereerd wachtwoord
			return ResponseEntity.notFound().build();
		}
		tak.getUsers().add(leiding);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{idtak}/leiding/{idleiding}")
	public ResponseEntity<Tak> deleteLeiderFromTak(@PathVariable (value="idtak") String idtak, @PathVariable (value="idleiding") String login) {
		Tak tak = takken.findOne(idtak);
		User leiding = users.findOne(login);
		if(tak == null || leiding == null) {
			return ResponseEntity.notFound().build();
		}
		tak.getUsers().remove(leiding);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{idtak}/activiteit")
	public List<Activiteit> getAllActiviteitenFromTak(@PathVariable (value="idtak") String idtak) {
		return takken.findOne(idtak).getActiviteiten();
	}
	
	@PostMapping("/{idtak}/activiteit")
	public ResponseEntity<Activiteit> addActiviteitToTak(@PathVariable (value="idtak") String idtak, @RequestBody Activiteit activiteit) {
		takken.findOne(idtak).addActiviteit(activiteit);
		return ResponseEntity.ok().build();
	}
	
}
