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

import com.medicamp.db.KindRepository;
import com.medicamp.db.TakRepository;
import com.medicamp.db.UserRepository;
import com.medicamp.model.Activiteit;
import com.medicamp.model.Kind;
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

	@Autowired
	KindRepository kinderen;
	
	@GetMapping("/{idtak}")
	public Tak getTakById(@PathVariable (value="idtak") int idtak) {
		return takken.findOne(idtak);
	}
	
	@PutMapping("/{idtak}")
	public ResponseEntity<Tak> updateTak(@PathVariable (value="idtak") int idtak, @RequestBody Tak tak) {
		Tak oldTak = takken.findOne(idtak);
		if(oldTak == null) {
			return ResponseEntity.notFound().build();
		}
		takken.save(tak);
		return ResponseEntity.ok().body(tak);
	}
	
	@DeleteMapping("/{idtak}")
	public ResponseEntity<Tak> deleteTak(@PathVariable (value="idtak") int idtak) {
		Tak found = takken.findOne(idtak);
		if(found == null) {
			return ResponseEntity.notFound().build();
		}
		takken.delete(idtak);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{idtak}/leiding")
	public List<User> getLeidingFromTak(@PathVariable (value="idtak") int idtak) {
		return takken.findOne(idtak).getUsers();
	}
	
	@PostMapping("/{idtak}/leiding/{login}")
	public ResponseEntity<Tak> addLeiderToTak(@PathVariable (value="idtak") int idtak, @PathVariable (value="login") String login) {
		Tak tak = takken.findOne(idtak);
		User leiding = users.findOne(login);
		if(tak == null) {
			return ResponseEntity.notFound().build();
		} else if (leiding == null) {
			//TODO nieuw account maken voor leider + mail sturen met gegenereerd wachtwoord
			return ResponseEntity.notFound().build();
		}
		tak.getUsers().add(leiding);
		takken.save(tak);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{idtak}/leiding/{login}")
	public ResponseEntity<Tak> deleteLeiderFromTak(@PathVariable (value="idtak") int idtak, @PathVariable (value="login") String login) {
		Tak tak = takken.findOne(idtak);
		User leiding = users.findOne(login);
		if(tak == null || leiding == null) {
			return ResponseEntity.notFound().build();
		}
		tak.getUsers().remove(leiding);
		takken.save(tak);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{idtak}/activiteit")
	public List<Activiteit> getAllActiviteitenFromTak(@PathVariable (value="idtak") int idtak) {
		return takken.findOne(idtak).getActiviteiten();
	}
	
	@PostMapping("/{idtak}/activiteit")
	public ResponseEntity<Activiteit> addActiviteitToTak(@PathVariable (value="idtak") int idtak, @RequestBody Activiteit activiteit) {
		Tak tak = takken.findOne(idtak);
		tak.addActiviteit(activiteit);
		takken.save(tak);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{idtak}/kind")
	public List<Kind> getAllKinderenFromTak(@PathVariable (value="idtak") int idtak) {
		return takken.findOne(idtak).getKinderen();
	}
	
	@PostMapping("/{idtak}/kind")
	public ResponseEntity<Tak> addKindToTak(@PathVariable (value="idtak") int idtak, @RequestBody int idkind) {
		Tak tak = takken.findOne(idtak);
		Kind kind = kinderen.findOne(idkind);
		if(tak == null || kind == null) {
			return ResponseEntity.notFound().build();
		}
		tak.getKinderen().add(kind);
		takken.save(tak);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{idtak}/kind/{idkind}")
	public ResponseEntity<Tak> deleteKindFromTak(@PathVariable (value="idtak") int idtak, @PathVariable (value="idkind") int idkind) {
		Tak tak = takken.findOne(idtak);
		Kind kind = kinderen.findOne(idkind);
		if(tak == null || kind == null) {
			return ResponseEntity.notFound().build();
		}
		tak.getKinderen().remove(kind);
		takken.save(tak);
		return ResponseEntity.ok().build();
	}
	
}
