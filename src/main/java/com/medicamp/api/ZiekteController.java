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
import com.medicamp.db.ZiekteRepository;
import com.medicamp.model.Dieet;
import com.medicamp.model.Kind;
import com.medicamp.model.Ziekte;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/ziekte")
public class ZiekteController {
	
	@Autowired
	ZiekteRepository ziektes;
	
	@Autowired
	KindRepository kinderen;
	
	@GetMapping()
	public List<Ziekte> getAllziektes() {
		return ziektes.findAll();
	}
	
	@GetMapping("/{idziekte}")
	public Ziekte getZiekteById(@PathVariable (value="idziekte") int idziekte ) {
		return ziektes.findOne(idziekte);
	}
	
	@PostMapping("/kind/{idkind}")
	public ResponseEntity<Dieet> addZiekte(@PathVariable (value="idkind") int idkind, @RequestBody Ziekte ziekte) {
		Kind k = kinderen.findOne(idkind);
		if(k == null) {
			return ResponseEntity.notFound().build();
		}
		k.getZiektes().add(ziekte);
		ziektes.save(ziekte);
		kinderen.save(k);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{idziekte}")
	public ResponseEntity<Ziekte> updateZiekte(@PathVariable (value="idziekte") int idziekte, @RequestBody Ziekte ziekte) {
		Ziekte z = ziektes.findOne(idziekte);
		if(z == null) {
			return ResponseEntity.notFound().build();
		}
		ziektes.save(ziekte);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{idziekte}")
	public ResponseEntity<Ziekte> deleteZiekte(@PathVariable (value="idziekte") int idziekte) {
		Ziekte z = ziektes.findOne(idziekte);
		if(z == null) {
			return ResponseEntity.notFound().build();
		}
		ziektes.delete(z);
		return ResponseEntity.ok().build();
	}

}
