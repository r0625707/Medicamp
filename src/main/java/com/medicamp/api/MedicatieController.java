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
import com.medicamp.db.MedicatieRepository;
import com.medicamp.model.Dieet;
import com.medicamp.model.Kind;
import com.medicamp.model.Medicatie;


@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/medicatie")
public class MedicatieController {
	
	@Autowired
	MedicatieRepository medicaties;
	
	@Autowired
	KindRepository kinderen;
	
	@GetMapping()
	public List<Medicatie> getAllMed() {
		return medicaties.findAll();
	}
	
	@GetMapping("/{idmedicatie}")
	public Medicatie getMedicatieById(@PathVariable (value="idmedicatie") int idmedicatie ) {
		return medicaties.findOne(idmedicatie);
	}
	
	@PostMapping("/kind/{idkind}")
	public ResponseEntity<Dieet> addMedicatie(@PathVariable (value="idkind") int idkind, @RequestBody Medicatie med) {
		Kind k = kinderen.findOne(idkind);
		if(k == null) {
			return ResponseEntity.notFound().build();
		}
		k.getMedicaties().add(med);
		medicaties.save(med);
		kinderen.save(k);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{idmedicatie}")
	public ResponseEntity<Medicatie> updateMedicatie(@PathVariable (value="idmedicatie") int idmedicatie, @RequestBody Medicatie medicatie) {
		Medicatie m = medicaties.findOne(idmedicatie);
		if(m == null) {
			return ResponseEntity.notFound().build();
		}
		m.setNaam(medicatie.getNaam());
		m.setOpmerking(medicatie.getOpmerking());
		medicaties.save(m);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{idmedicatie}")
	public ResponseEntity<Medicatie> deleteMedicatie(@PathVariable (value="idmedicatie") int idmedicatie) {
		Medicatie m = medicaties.findOne(idmedicatie);
		if(m == null) {
			return ResponseEntity.notFound().build();
		}
		medicaties.delete(m);
		return ResponseEntity.ok().build();
	}

}