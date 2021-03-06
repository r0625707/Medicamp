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
import com.medicamp.db.UserRepository;
import com.medicamp.db.VoogdRepository;

import com.medicamp.model.Voogd;
import com.medicamp.model.Kind;
import com.medicamp.model.Tak;
import com.medicamp.model.User;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/voogd")
public class VoogdController {
	
	@Autowired
	VoogdRepository voogden;
	
	@Autowired
	UserRepository users;
	
	@Autowired
	KindRepository kinderen;
	
		
	@GetMapping
	public List<Voogd> getAllvoogden() {
		return voogden.findAll();
	}
	
		
	@PostMapping
	public ResponseEntity<Voogd> addVoogd(@RequestBody Voogd voogd) {
		voogden.save(voogd);
		return ResponseEntity.ok().body(voogd);
	}
	
	@PostMapping("/{login}")
	public ResponseEntity<User> addVoogd(@PathVariable (value="login") String login, @RequestBody Voogd voogd) {
		User user = users.findOne(login);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		voogd.setUser(user);
		user.addVoogd(voogd);
		voogden.save(voogd);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{login}/kind/{idkind}")
	public ResponseEntity<Voogd> addVoogdtoKind(@PathVariable (value="login") String login, @PathVariable (value="idkind") int idkind, @RequestBody Voogd voogd) {
		User user = users.findOne(login);
		Kind kind = kinderen.findOne(idkind);
		if(user == null || kind == null) {
			return ResponseEntity.notFound().build();
		}
		voogd.setUser(user);
		user.addVoogd(voogd);
		kind.getVoogden().add(voogd);
		voogden.save(voogd);
		kinderen.save(kind);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{idvoogd}")
	public Voogd getVoogdById(@PathVariable (value = "idvoogd") int idvoogd) {
		return voogden.findOne(idvoogd);
	}
	
	@PutMapping("/{idvoogd}")
	public ResponseEntity<Voogd> updateVoogd(@PathVariable (value = "idvoogd") int idvoogd, @RequestBody Voogd voogd) {
		Voogd oldVoogd = voogden.findOne(idvoogd);
		if(oldVoogd == null) {
			return ResponseEntity.notFound().build();
		}
		voogd.setIdvoogd(idvoogd);
		voogd.setUser(oldVoogd.getUser());
		voogden.save(voogd);
		return ResponseEntity.ok().body(voogd);
	}
	
	@DeleteMapping("/{idvoogd}")
	public ResponseEntity<Voogd> deleteVoogd(@PathVariable (value = "idvoogd") int idvoogd) {
		Voogd found = voogden.findOne(idvoogd);
		if(found == null) {
			return ResponseEntity.notFound().build();
		}
		voogden.delete(idvoogd);
		return ResponseEntity.ok().build();
	}
	
	

}
