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

import com.medicamp.db.ZiekteRepository;

import com.medicamp.model.Ziekte;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/ziekte")
public class ZiekteController {
	
	@Autowired
	ZiekteRepository ziekten;
	
	@GetMapping()
	public List<Ziekte> getAllZiekten() {
		return ziekten.findAll();
	}
	
	@GetMapping("/{idziekte}")
	public Ziekte getZiekteById(@PathVariable (value="idziekte") int idziekte ) {
		return ziekten.findOne(idziekte);
	}
	
	@PutMapping("/{idziekte}")
	public ResponseEntity<Ziekte> updateZiekte(@PathVariable (value="idziekte") int idziekte, @RequestBody Ziekte ziekte) {
		Ziekte z = ziekten.findOne(idziekte);
		if(z == null) {
			return ResponseEntity.notFound().build();
		}
		ziekten.save(ziekte);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{idziekte}")
	public ResponseEntity<Ziekte> deleteZiekte(@PathVariable (value="idziekte") int idziekte) {
		Ziekte z = ziekten.findOne(idziekte);
		if(z == null) {
			return ResponseEntity.notFound().build();
		}
		ziekten.delete(z);
		return ResponseEntity.ok().build();
	}

}
