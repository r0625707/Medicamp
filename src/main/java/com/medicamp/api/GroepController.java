package com.medicamp.api;

import java.util.ArrayList;
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
import com.medicamp.model.User;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/groep")
public class GroepController {

	@Autowired
	GroepRepository groepen;

	@Autowired
	TakRepository takken;

	@GetMapping
	public List<GroepInfoBean> getAllGroepen() {

		List<GroepInfoBean> result = new ArrayList<GroepInfoBean>();

		for (Groep g : groepen.findAll()) {

			result.add(new GroepInfoBean(g.getNaam(), g.getPostcode(), g.getStraat(), g.getBus(), g.getEmail(),
					g.getLink(), g.getUsers(), g.getIdgroep(), g.getHuisnr()));

		}
		;

		return result;
	}

	@PostMapping
	public ResponseEntity<Groep> addGroep(@RequestBody Groep groep) {
		groepen.save(groep);
		return ResponseEntity.ok().body(groep);
	}

	@GetMapping("/{idgroep}")
	public Groep getGroepById(@PathVariable(value = "idgroep") int idgroep) {
		return groepen.findOne(idgroep);
	}

	@PutMapping("/{idgroep}")
	public ResponseEntity<Groep> updateGroep(@PathVariable(value = "idgroep") int idgroep, @RequestBody Groep groep) {
		Groep oldGroep = groepen.findOne(idgroep);
		if (oldGroep == null) {
			return ResponseEntity.notFound().build();
		}
		groep.setIdgroep(idgroep);
		groepen.save(groep);
		return ResponseEntity.ok().body(groep);
	}

	@DeleteMapping("/{idgroep}")
	public ResponseEntity<Groep> deleteGroep(@PathVariable(value = "idgroep") int idgroep) {
		Groep found = groepen.findOne(idgroep);
		if (found == null) {
			return ResponseEntity.notFound().build();
		}
		groepen.delete(idgroep);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{idgroep}/tak")
	public List<Tak> getAllTakkenFromGroep(@PathVariable(value = "idgroep") int idgroep) {
		Groep groep = groepen.findOne(idgroep);
		return groep.getTakken();
	}

	@PostMapping("/{idgroep}/tak")
	public ResponseEntity<Tak> addTak(@PathVariable(value = "idgroep") int idgroep, @RequestBody Tak tak) {
		tak.setGroep(groepen.findOne(idgroep));
		takken.save(tak);
		return ResponseEntity.ok().body(tak);
	}

	class GroepInfoBean {

		String naam, voornaam, postcode, straat, bus, email, link;
		int idGroep, huisnr;
		List<User> leiding;

		GroepInfoBean() {
		}

		public GroepInfoBean(String naam, String postcode, String straat, String bus, String email, String link,
				List<User> leiding, int idGroep, int huisnr) {

			super();
			this.naam = naam;
			this.postcode = postcode;
			this.straat = straat;
			this.bus = bus;
			this.email = email;
			this.link = link;
			this.leiding = leiding;
			this.idGroep = idGroep;
			this.huisnr = huisnr;
		}

		public String getNaam() {
			return naam;
		}

		public void setNaam(String naam) {
			this.naam = naam;
		}

		public String getVoornaam() {
			return voornaam;
		}

		public void setVoornaam(String voornaam) {
			this.voornaam = voornaam;
		}

		public String getPostcode() {
			return postcode;
		}

		public void setPostcode(String postcode) {
			this.postcode = postcode;
		}

		public String getStraat() {
			return straat;
		}

		public void setStraat(String straat) {
			this.straat = straat;
		}

		public String getBus() {
			return bus;
		}

		public void setBus(String bus) {
			this.bus = bus;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public List<User> getLeiding() {
			return leiding;
		}

		public void setLeiding(List<User> leiding) {
			this.leiding = leiding;
		}

		public int getIdGroep() {
			return idGroep;
		}

		public void setIdGroep(int idGroep) {
			this.idGroep = idGroep;
		}

		public int getHuisnr() {
			return huisnr;
		}

		public void setHuisnr(int huisnr) {
			this.huisnr = huisnr;
		}

	}

}
