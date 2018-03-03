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

import com.medicamp.db.DieetRepository;
import com.medicamp.db.KindRepository;
import com.medicamp.model.Kind;
import com.medicamp.model.Dieet;


@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/dieet")
public class DieetController {
	
	@Autowired
	DieetRepository dieeten;
	
	@Autowired
	KindRepository kinderen;
	
	@GetMapping()
	public List<Dieet> getAllDieeten() {
		return dieeten.findAll();
	}
	
	@GetMapping("/{iddieet}")
	public Dieet getDieetById(@PathVariable (value="iddieet") int iddieet ) {
		return dieeten.findOne(iddieet);
	}
	
	@PostMapping("/kind/{idkind}")
	public ResponseEntity<Dieet> addDieet(@PathVariable (value="idkind") int idkind, @RequestBody Dieet dieet) {
		Kind k = kinderen.findOne(idkind);
		if(k == null) {
			return ResponseEntity.notFound().build();
		}
		k.getDieeten().add(dieet);
		dieeten.save(dieet);
		kinderen.save(k);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{iddieet}")
	public ResponseEntity<Dieet> updateDieet(@PathVariable (value="iddieet") int iddieet, @RequestBody Dieet dieet) {
		Dieet d = dieeten.findOne(iddieet);
		if(d == null) {
			return ResponseEntity.notFound().build();
		}
		dieeten.save(dieet);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{iddieet}")
	public ResponseEntity<Dieet> deleteDieet(@PathVariable (value="iddieet") int iddieet) {
		Dieet d = dieeten.findOne(iddieet);
		if(d == null) {
			return ResponseEntity.notFound().build();
		}
		dieeten.delete(d);
		return ResponseEntity.ok().build();
	}

}
