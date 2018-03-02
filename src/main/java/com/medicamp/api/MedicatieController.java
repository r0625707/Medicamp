package com.medicamp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicamp.db.MedicatieRepository;
import com.medicamp.model.Medicatie;


@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/medicatie")
public class MedicatieController {
	
	@Autowired
	MedicatieRepository medicatieen;
	
	@GetMapping()
	public List<Medicatie> getAllMed() {
		return medicatieen.findAll();
	}
	
	@GetMapping("/{idmedicatie}")
	public Medicatie getMedicatieById(@PathVariable (value="idmedicatie") int idmedicatie ) {
		return medicatieen.findOne(idmedicatie);
	}
	
	@PutMapping("/{idmedicatie}")
	public ResponseEntity<Medicatie> updateMedicatie(@PathVariable (value="idmedicatie") int idmedicatie, @RequestBody Medicatie medicatie) {
		Medicatie m = medicatieen.findOne(idmedicatie);
		if(m == null) {
			return ResponseEntity.notFound().build();
		}
		medicatieen.save(medicatie);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{idmedicatie}")
	public ResponseEntity<Medicatie> deleteMedicatie(@PathVariable (value="idmedicatie") int idmedicatie) {
		Medicatie m = medicatieen.findOne(idmedicatie);
		if(m == null) {
			return ResponseEntity.notFound().build();
		}
		medicatieen.delete(m);
		return ResponseEntity.ok().build();
	}

}