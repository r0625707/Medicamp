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
import com.medicamp.db.MedicatieRepository;
import com.medicamp.db.UserRepository;
import com.medicamp.db.VoogdRepository;
import com.medicamp.db.ZiekteRepository;
import com.medicamp.model.Dieet;
import com.medicamp.model.Kind;
import com.medicamp.model.Medicatie;
import com.medicamp.model.User;
import com.medicamp.model.Voogd;
import com.medicamp.model.Ziekte;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/api/kind")
public class KindController {
	
	@Autowired
	KindRepository kinderen;
	
	@Autowired
	VoogdRepository voogden;
	
	@Autowired
	ZiekteRepository ziektes;
	
	@Autowired
	DieetRepository dieeten;
	
	@Autowired
	MedicatieRepository medicaties;
	
	@Autowired
	UserRepository users;
	
	@PostMapping
	public ResponseEntity<Kind> addKind(@RequestBody Kind kind) {
		kinderen.save(kind);
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping()
	public List<Kind> getAllKinderen() {
		return kinderen.findAll();
	}
	
	@PostMapping("/{login}")
	public ResponseEntity<User> addKind(@PathVariable (value="login") String login, @RequestBody Kind kind) {
		User user = users.findOne(login);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}
		kind.setUser(user);
		user.addKind(kind);
		kinderen.save(kind);
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/{idkind}")
	public Kind getKindByIk(@PathVariable (value="idkind") int idkind) {
		return kinderen.findOne(idkind);
	}
	
	@PutMapping("/{idkind}")
	public ResponseEntity<Kind> updateKind(@PathVariable (value="idkind") int idkind, @RequestBody Kind kind) {
		Kind oldKind = kinderen.findOne(idkind);
		if(oldKind == null) {
			return ResponseEntity.notFound().build();
		}
		oldKind.setNaam(kind.getNaam());
		oldKind.setVoornaam(kind.getVoornaam());
		oldKind.setGebdatum(kind.getGebdatum());
		oldKind.setDafi(kind.getDafi());
		oldKind.setZwemmen(kind.getZwemmen());
		oldKind.setSport(kind.getSport());
		oldKind.setOpmerking(kind.getOpmerking());
		kinderen.save(oldKind);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{idkind}")
	public ResponseEntity<Kind> deleteKind(@PathVariable (value="idkind") int idkind) {
		Kind found = kinderen.findOne(idkind);
		if(found == null) {
			return ResponseEntity.notFound().build();
		}
		kinderen.delete(idkind);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{idkind}/voogd")
	public List<Voogd> getAllVoogdenFromKind(@PathVariable (value="idkind") int idkind) {
		return kinderen.findOne(idkind).getVoogden();
	}
	
	@PostMapping("/{idkind}/voogd/{idvoogd}")
	public ResponseEntity<Kind> addVoogdToKind(@PathVariable (value="idkind") int idkind, @PathVariable (value="idvoogd") int idvoogd) {
		Kind kind = kinderen.findOne(idkind);
		Voogd voogd = voogden.findOne(idvoogd);
		if(kind == null || voogd == null) {
			return ResponseEntity.notFound().build();
		}
		kind.getVoogden().add(voogd);
		kinderen.save(kind);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{idkind}/voogd/{idvoogd}")
	public ResponseEntity<Kind> deleteVoogdFromKind(@PathVariable (value="idkind") int idkind, @PathVariable (value="idvoogd") int idvoogd) {
		Kind kind = kinderen.findOne(idkind);
		Voogd voogd = voogden.findOne(idvoogd);
		if(kind == null || voogd == null) {
			return ResponseEntity.notFound().build();
		}
		kind.getVoogden().remove(voogd);
		kinderen.save(kind);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{idkind}/ziekte")
	public List<Ziekte> getAllZiektesFromKind(@PathVariable (value="idkind") int idkind) {
		return kinderen.findOne(idkind).getZiektes();
	}
	
	@PostMapping("/{idkind}/ziekte/{idziekte}")
	public ResponseEntity<Kind> addZiekteToKind(@PathVariable (value="idkind") int idkind, @PathVariable (value="idziekte") int idziekte) {
		Kind kind = kinderen.findOne(idkind);
		Ziekte ziekte = ziektes.findOne(idziekte);
		if(kind == null || ziekte == null) {
			return ResponseEntity.notFound().build();
		}
		kind.getZiektes().add(ziekte);
		kinderen.save(kind);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{idkind}/ziekte/{idziekte}")
	public ResponseEntity<Kind> deleteZiekteFromKind(@PathVariable (value="idkind") int idkind, @PathVariable (value="idziekte") int idziekte) {
		Kind kind = kinderen.findOne(idkind);
		Ziekte ziekte = ziektes.findOne(idziekte);
		if(kind == null || ziekte == null) {
			return ResponseEntity.notFound().build();
		}
		kind.getZiektes().remove(ziekte);
		kinderen.save(kind);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{idkind}/dieet")
	public List<Dieet> getAllDieetenFromKind(@PathVariable (value="idkind") int idkind) {
		return kinderen.findOne(idkind).getDieeten();
	}
	
	@PostMapping("/{idkind}/dieet/{iddieet}")
	public ResponseEntity<Kind> addDieetToKind(@PathVariable (value="idkind") int idkind, @PathVariable (value="iddieet") int iddieet) {
		Kind kind = kinderen.findOne(idkind);
		Dieet dieet = dieeten.findOne(iddieet);
		if(kind == null || dieet == null) {
			return ResponseEntity.notFound().build();
		}
		kind.getDieeten().add(dieet);
		kinderen.save(kind);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{idkind}/dieet/{iddieet}")
	public ResponseEntity<Kind> deleteDieetFromKind(@PathVariable (value="idkind") int idkind, @PathVariable (value="iddieet") int iddieet) {
		Kind kind = kinderen.findOne(idkind);
		Dieet dieet = dieeten.findOne(iddieet);
		if(kind == null || dieet == null) {
			return ResponseEntity.notFound().build();
		}
		kind.getDieeten().remove(dieet);
		kinderen.save(kind);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{idkind}/medicatie")
	public List<Medicatie> getMedicatieFromKind(@PathVariable (value="idkind") int idkind) {
		return kinderen.findOne(idkind).getMedicaties();
	}
	
	@PostMapping("/{idkind}/medicatie/{idmedicatie}")
	public ResponseEntity<Kind> addMedicatieToKind(@PathVariable (value="idkind") int idkind, @PathVariable (value="idmedicatie") int idmedicatie) {
		Kind kind = kinderen.findOne(idkind);
		Medicatie medicatie = medicaties.findOne(idmedicatie);
		if(kind == null || medicatie == null) {
			return ResponseEntity.notFound().build();
		}
		kind.getMedicaties().add(medicatie);
		kinderen.save(kind);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{idkind}/medicatie/{idmedicatie}")
	public ResponseEntity<Kind> deleteMedicatieFromKind(@PathVariable (value="idkind") int idkind, @PathVariable (value="idmedicatie") int idmedicatie) {
		Kind kind = kinderen.findOne(idkind);
		Medicatie medicatie = medicaties.findOne(idmedicatie);
		if(kind == null || medicatie == null) {
			return ResponseEntity.notFound().build();
		}
		kind.getMedicaties().remove(medicatie);
		kinderen.save(kind);
		return ResponseEntity.ok().build();
	}

}
