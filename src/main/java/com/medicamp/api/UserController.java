package com.medicamp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicamp.model.Activiteit;
import com.medicamp.model.Dieet;
import com.medicamp.model.Groep;
import com.medicamp.model.Kind;
import com.medicamp.model.Medicatie;
import com.medicamp.model.Tak;
import com.medicamp.model.Tijdstip;
import com.medicamp.model.User;
import com.medicamp.model.Voogd;
import com.medicamp.model.Ziekte;

import com.medicamp.db.ActiviteitRepository;
import com.medicamp.db.DieetRepository;
import com.medicamp.db.GroepRepository;
import com.medicamp.db.KindRepository;
import com.medicamp.db.MedicatieRepository;
import com.medicamp.db.TakRepository;
import com.medicamp.db.UserRepository;
import com.medicamp.db.VoogdRepository;
import com.medicamp.mobiel.ApiFixer;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserRepository users;

	@Autowired
	KindRepository kinderen;

	@Autowired
	VoogdRepository voogden;

	@Autowired
	ActiviteitRepository activiteiten;

	@Autowired
	DieetRepository dieten;

	@Autowired
	GroepRepository groepen;

	@Autowired
	MedicatieRepository medicaties;

	@Autowired
	TakRepository takken;

	@GetMapping("/{login}/overview")
	public ResponseBean getUserInfo(@PathVariable(value = "login") String login) {

		User user = users.findOne(login);
		ResponseBean response = new ResponseBean();
		response.user = user;
		response.kinderen = user.getKinderen();
		response.takken = user.getTakken();
		response.groepen = user.getGroepen();
		response.voogden = user.getVoogden();
		return response;
	}

    //@PreAuthorize("isAuthorisedMethod('getAllUsers')")
	@GetMapping()
	public List<User> getAllUsers() {
		Authentication var = SecurityContextHolder.getContext().getAuthentication();
		return users.findAll();
	}

	// @PreAuthorize("hasRole('0')")
	@GetMapping("/current")
	public String getCurrentUser() {
		Authentication var = SecurityContextHolder.getContext().getAuthentication();
		return (String) SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
	}

    //@PreAuthorize("isAuthorisedMethodAndUser('getAllKinderen',#login)")
	@GetMapping("/{login}/kind")
	public ResponseEntity<List<Kind>> getAllKinderen(@PathVariable(value = "login") String login) {
		User user = users.findOne(login);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user.getKinderen());
	}

	@GetMapping("/{login}/voogd")
	public ResponseEntity<List<Voogd>> getAllVoogden(@PathVariable(value = "login") String string) {
		User user = users.findOne(string);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user.getVoogden());
	}

	@GetMapping("/{login}/tak")
	public ResponseEntity<List<Tak>> getAlltakken(@PathVariable(value = "login") String string) {
		User user = users.findOne(string);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user.getTakken());
	}

	@GetMapping("/{login}/groep")
	public ResponseEntity<List<Groep>> getAllGroepen(@PathVariable(value = "login") String login) {
		User user = users.findOne(login);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user.getGroepen());
	}

	@PostMapping()
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User find = users.findOne(user.getLogin());
		if (find != null) {
			return ResponseEntity.status(500).build();
		}
		String password = user.getPassword();
		user.setPasswordHashed(password);
		users.save(user);
		return ResponseEntity.ok().body(user);
	}

	@GetMapping("/{login}/")
	public ResponseEntity<User> getUser(@PathVariable(value = "login") String string) {
		User user = users.findOne(string);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(user);
	}

	@PutMapping("/{login}/")
	public ResponseEntity<User> updateUser(@PathVariable(value = "login") String string, @RequestBody User user) {

		User oldUser = users.findOne(string);

		if (oldUser == null) {
			return ResponseEntity.notFound().build();
		}

		// oldUser.setLogin(user.getLogin());
		oldUser.setNaam(user.getNaam());
		oldUser.setRole(user.getRole());
		oldUser.setTel(user.getTel());
		oldUser.setVoornaam(user.getVoornaam());

		User updatedUser = users.save(oldUser);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{login}/")
	public ResponseEntity<User> deleteUser(@PathVariable(value = "login") String string) {
		User note = users.findOne(string);
		if (note == null) {
			return ResponseEntity.notFound().build();
		}
		users.delete(note);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/login")
	public boolean login(@RequestBody String login, @RequestBody String password) {
		User user = users.findOne(login);
		if (user == null) {
			return false;
		}
		return user.isCorrectPassword(password);
	}

	class ResponseBean {

		private User user;
		private List<Activiteit> activiteiten;
		private List<Dieet> dieeten;
		private List<Groep> groepen;
		private List<Kind> kinderen;
		private List<Medicatie> medicaties;
		private List<Tak> takken;
		private List<Tijdstip> tijdstippen;
		private List<User> users;
		private List<Voogd> voogden;
		private List<Ziekte> ziektes;

		public ResponseBean() {

		}

		public ResponseBean(User user, List<Activiteit> activiteiten, List<Dieet> dieeten, List<Groep> groepen,
				List<Kind> kinderen, List<Medicatie> medicaties, List<Tak> takken, List<Tijdstip> tijdstippen,
				List<User> users, List<Voogd> voogden, List<Ziekte> ziektes) {
			super();
			this.user = user;
			this.activiteiten = activiteiten;
			this.dieeten = dieeten;
			this.groepen = groepen;
			this.kinderen = kinderen;
			this.medicaties = medicaties;
			this.takken = takken;
			this.tijdstippen = tijdstippen;
			this.users = users;
			this.voogden = voogden;
			this.ziektes = ziektes;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public List<Activiteit> getActiviteiten() {
			return activiteiten;
		}

		public void setActiviteiten(List<Activiteit> activiteiten) {
			this.activiteiten = activiteiten;
		}

		public List<Dieet> getDieeten() {
			return dieeten;
		}

		public void setDieeten(List<Dieet> dieeten) {
			this.dieeten = dieeten;
		}

		public List<Groep> getGroepen() {
			return groepen;
		}

		public void setGroepen(List<Groep> groepen) {
			this.groepen = groepen;
		}

		public List<Kind> getKinderen() {
			return kinderen;
		}

		public void setKinderen(List<Kind> kinderen) {
			this.kinderen = kinderen;
		}

		public List<Medicatie> getMedicaties() {
			return medicaties;
		}

		public void setMedicaties(List<Medicatie> medicaties) {
			this.medicaties = medicaties;
		}

		public List<Tak> getTakken() {
			return takken;
		}

		public void setTakken(List<Tak> takken) {
			this.takken = takken;
		}

		public List<Tijdstip> getTijdstippen() {
			return tijdstippen;
		}

		public void setTijdstippen(List<Tijdstip> tijdstippen) {
			this.tijdstippen = tijdstippen;
		}

		public List<User> getUsers() {
			return users;
		}

		public void setUsers(List<User> users) {
			this.users = users;
		}

		public List<Voogd> getVoogden() {
			return voogden;
		}

		public void setVoogden(List<Voogd> voogden) {
			this.voogden = voogden;
		}

		public List<Ziekte> getZiektes() {
			return ziektes;
		}

		public void setZiektes(List<Ziekte> ziektes) {
			this.ziektes = ziektes;
		}

	}

}
