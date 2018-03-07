package com.medicamp.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicamp.db.ActiviteitRepository;
import com.medicamp.model.Activiteit;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/activiteit")
public class ActiviteitController {
	
	@Autowired
	ActiviteitRepository activiteiten;
	
	@PreAuthorize("isAuthorisedMethodAndUser('getActiviteitById')")
	@GetMapping("/{idactiviteit}")
	public Activiteit getActiviteitById(@PathVariable (value="idactiviteit") int idactiviteit ) {
		return activiteiten.findOne(idactiviteit);
	}
	
	@PreAuthorize("isAuthorisedMethodAndUser('updateActivieit')")
	@PutMapping("/{idactiviteit}")
	public ResponseEntity<Activiteit> updateActiviteit(@PathVariable (value="idactiviteit") int idactiviteit, @RequestBody Activiteit activiteit) {
		Activiteit act = activiteiten.findOne(idactiviteit);
		if(act == null) {
			return ResponseEntity.notFound().build();
		}
		activiteiten.save(activiteit);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{idactiviteit}")
	public ResponseEntity<Activiteit> deleteActiviteit(@PathVariable (value="idactiviteit") int idactiviteit) {
		Activiteit act = activiteiten.findOne(idactiviteit);
		if(act == null) {
			return ResponseEntity.notFound().build();
		}
		activiteiten.delete(act);
		return ResponseEntity.ok().build();
	}

}
