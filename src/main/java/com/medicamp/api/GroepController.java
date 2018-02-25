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

import com.medicamp.db.GroepRepository;
import com.medicamp.db.TakRepository;
import com.medicamp.model.Groep;
import com.medicamp.model.Tak;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/groep")
public class GroepController {
	
	@Autowired
	GroepRepository groepen;
	
	@Autowired
	TakRepository takken;
	
	@GetMapping
	public List<Groep> getAllGroepen() {
		return groepen.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Groep> addGroep(@RequestBody Groep groep) {
		groepen.save(groep);
		return ResponseEntity.ok().body(groep);
	}
	
	@GetMapping("/{idgroep}")
	public Groep getGroepById(@PathVariable (value = "idgroep") String idgroep) {
		return groepen.findOne(idgroep);
	}
	
	@PutMapping("/{idgroep}")
	public ResponseEntity<Groep> updateGroep(@PathVariable (value = "idgroep") String idgroep, @RequestBody Groep groep) {
		Groep oldGroep = groepen.findOne(idgroep);
		if(oldGroep == null) {
			return ResponseEntity.notFound().build();
		}
		groep.setIdgroep(Integer.parseInt(idgroep));
		groepen.save(groep);
		return ResponseEntity.ok().body(groep);
	}
	
	@DeleteMapping("/{idgroep}")
	public ResponseEntity<Groep> deleteGroep(@PathVariable (value = "idgroep") String idgroep) {
		Groep found = groepen.findOne(idgroep);
		if(found == null) {
			return ResponseEntity.notFound().build();
		}
		groepen.delete(idgroep);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{idgroep}/tak")
	public List<Tak> getAllTakkenFromGroep(@PathVariable (value = "idgroep") String idgroep) {
		Groep groep = groepen.findOne(idgroep);
		return groep.getTakken();
	}
	
	@PostMapping("/{idgroep}/tak")
	public ResponseEntity<Tak> addTak(@PathVariable (value = "idgroep") String idgroep, @RequestBody Tak tak) {
		tak.setGroep(groepen.findOne(idgroep));
		takken.save(tak);
		return ResponseEntity.ok().body(tak);
	}

}
