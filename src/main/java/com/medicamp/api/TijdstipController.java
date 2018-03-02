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

import com.medicamp.db.MedicatieRepository;
import com.medicamp.db.TijdstipRepository;
import com.medicamp.model.Medicatie;
import com.medicamp.model.Tijdstip;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/tijdstip")
public class TijdstipController {
	
	@Autowired
	TijdstipRepository tijdstippen;
	
	@Autowired
	MedicatieRepository medicaties;
	
	@GetMapping()
	public List<Tijdstip> getAllTijd() {
		return tijdstippen.findAll();
	}
	
	@PostMapping("/{idmedicatie}")
	public ResponseEntity<Tijdstip> addTijdstip(@PathVariable (value="idmedicatie") int idmedicatie, @RequestBody Tijdstip tijdstip) {
		Medicatie med = medicaties.findOne(idmedicatie);
		tijdstip.setMedicatie(med);
		tijdstippen.save(tijdstip);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{idtijdstip}")
	public Tijdstip getTijdstipById(@PathVariable (value="idtijdstip") int idtijdstip ) {
		return tijdstippen.findOne(idtijdstip);
	}
	
	@PutMapping("/{idtijdstip}")
	public ResponseEntity<Tijdstip> updateTijdstip(@PathVariable (value="idtijdstip") int idtijdstip, @RequestBody Tijdstip tijdstip) {
		Tijdstip t = tijdstippen.findOne(idtijdstip);
		if(t == null) {
			return ResponseEntity.notFound().build();
		}
		tijdstippen.save(tijdstip);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{idtijdstip}")
	public ResponseEntity<Tijdstip> deleteTijdstip(@PathVariable (value="idtijdstip") int idtijdstip) {
		Tijdstip t = tijdstippen.findOne(idtijdstip);
		if(t == null) {
			return ResponseEntity.notFound().build();
		}
		tijdstippen.delete(t);
		return ResponseEntity.ok().build();
	}

}