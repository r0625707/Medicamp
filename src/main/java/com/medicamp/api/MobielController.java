package com.medicamp.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicamp.api.MobielController.ResponseObject.InnerGroep;
import com.medicamp.api.MobielController.ResponseObject.InnerKind;
import com.medicamp.api.MobielController.ResponseObject.InnerKind.InnerInnerMedicatie;
import com.medicamp.api.MobielController.ResponseObject.InnerTak;
import com.medicamp.api.MobielController.ResponseObject.InnerUser;
import com.medicamp.db.ActiviteitRepository;
import com.medicamp.db.DieetRepository;
import com.medicamp.db.GroepRepository;
import com.medicamp.db.KindRepository;
import com.medicamp.db.MedicatieRepository;
import com.medicamp.db.TakRepository;
import com.medicamp.db.UserRepository;
import com.medicamp.db.VoogdRepository;
import com.medicamp.mobiel.ApiFixer;
import com.medicamp.model.Dieet;
import com.medicamp.model.Medicatie;
import com.medicamp.model.Tak;
import com.medicamp.model.Tijdstip;
import com.medicamp.model.Groep;
import com.medicamp.model.Kind;
import com.medicamp.model.Voogd;
import com.medicamp.model.Ziekte;

import javassist.expr.NewArray;

@RestController
@CrossOrigin(origins = "http://localhost:3000")

@RequestMapping("/api")
public class MobielController {

	@Autowired
	ApiFixer f;

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

	@GetMapping("/{login}/mobiel")
	public /* ResponseEntity<List<Tak>> */List<Object> mobiele(
			@RequestHeader(value = "Authorization") String authorization,
			@PathVariable(value = "login") String string) {

		return f.fix(string, authorization);

		/*
		 * User user = users.findOne(string);
		 * 
		 * if (user == null) { return ResponseEntity.notFound().build(); } return
		 * ResponseEntity.ok().body(user.getTakken());
		 */
	}

	@GetMapping("/{login}/mobiel_test")
	public ResponseObject mobiele(@PathVariable(value = "login") String login) {

		ResponseObject response = new ResponseObject();
		com.medicamp.model.User user = users.getOne(login);

		// seting InnerUser
		InnerUser innerUser = new InnerUser(user.getLogin(), user.getNaam(), user.getPassword(), user.getTel(),
				user.getVoornaam(), user.getRole());
		response.setUser(innerUser);

		// seting groeps of user

		List<Groep> groepenList = groepen.findAllGroepenForUser(login);
		List<InnerGroep> innerGroepen = new ArrayList<>();
		List<Tak> takkenlist = new ArrayList<>();
		for (Groep g : groepenList) {

			InnerGroep innerGroep = new InnerGroep(g.getBus(), g.getEmail(), g.getLink(), g.getPlaats(), g.getPlaats(),
					g.getPostcode(), g.getStraat(), g.getIdgroep(), g.getHuisnr(), new ArrayList<>());
			List<Integer> oldList = takken.getTakkenIdForGroep(g.getIdgroep());
			// adding takken per groep
			for (Integer i : oldList) {
				takkenlist.add(takken.findOne(i));
			}
			List<String> newList = new ArrayList<String>(oldList.size());
			for (Integer myInt : oldList) {
				newList.add(String.valueOf(myInt));
			}
			List<String> takkenIds = newList;
			innerGroep.setTakkenids(takkenIds);
			innerGroepen.add(innerGroep);
		}
		response.setGroepen(innerGroepen);
		// initialising list of visible kinderen

		Set<Kind> kinderenSet = new HashSet<>();

		// setting takken
		List<InnerTak> innerTakkenList = new ArrayList<>();
		List<Tak> takkenlistleiding = takken.findAllForUser(login);
		takkenlist.addAll(takkenlistleiding);
		for (Tak t : takkenlist) {
			List<Integer> oldList = kinderen.getIdKindForTak(t.getIdtak());
			List<String> newList = new ArrayList<String>(oldList.size());
			for (Integer myInt : oldList) {
				newList.add(String.valueOf(myInt));
			}
			List<String> takkenIds = newList;
			innerTakkenList.add(new InnerTak(t.getIdtak(), t.getNaam(), t.getOmschrijving(), newList, t.getGroep().getNaam()));

			// adding all kinderen from all takken
			for (Kind k : t.getKinderen()) {

				kinderenSet.add(k);

			}

		}
		response.setTakken(innerTakkenList);

		// setting kinderen
		
		List<Kind> kinderenList = new ArrayList<>(kinderenSet);
		List<InnerKind> innerkinderen = new ArrayList<>();
		for (Kind k : kinderenList) {

			List<String> takkenIDlistkind = new ArrayList<>();
			for (Tak t : k.getTakken()) {
				takkenIDlistkind.add(Integer.toString(t.getIdtak()));
			}

			// setting medicatie

			List<Medicatie> medicatielist = k.getMedicaties();
			List<InnerInnerMedicatie> innnerInnerMedicatielist = new ArrayList<>();
			for (Medicatie m : medicatielist) {
				innnerInnerMedicatielist.add(
						new InnerInnerMedicatie(m.getIdmedicatie(), m.getNaam(), m.getOpmerking(), m.getTijdstippen()));

			}

			InnerKind innerKind = new InnerKind(k.getIdkind(), k.getDafi(), k.getSport(), k.getZwemmen(),
					k.getGebdatum(), k.getNaam(), k.getOpmerking(), k.getVoornaam(), k.getDieeten(),
					innnerInnerMedicatielist, k.getVoogden(), k.getZiektes(), takkenIDlistkind);

			innerkinderen.add(innerKind);

		}
		response.setKinderen(innerkinderen);

		return response;

	}

	public static class ResponseObject {

		public InnerUser user;
		public List<InnerGroep> groepen;
		public List<InnerKind> kinderen;
		public List<InnerTak> takken;

		public ResponseObject() {

		}

		public ResponseObject(InnerUser user, List<InnerGroep> groepen, List<InnerKind> kinderen,
				List<InnerTak> takken) {

			this.user = user;
			this.groepen = groepen;
			this.kinderen = kinderen;
			this.takken = takken;
		}

		public InnerUser getUser() {
			return user;
		}

		public void setUser(InnerUser user) {
			this.user = user;
		}

		public List<InnerGroep> getGroepen() {
			return groepen;
		}

		public void setGroepen(List<InnerGroep> groepen) {
			this.groepen = groepen;
		}

		public List<InnerKind> getKinderen() {
			return kinderen;
		}

		public void setKinderen(List<InnerKind> kinderen) {
			this.kinderen = kinderen;
		}

		public List<InnerTak> getTakken() {
			return takken;
		}

		public void setTakken(List<InnerTak> takken) {
			this.takken = takken;
		}

		public static class InnerUser {

			private String login, naam, password, tel, voornaam;
			private int role;

			public InnerUser() {

			}

			public InnerUser(String login, String naam, String password, String tel, String voornaam, int role) {
				super();
				this.login = login;
				this.naam = naam;
				this.password = password;

				this.tel = tel;
				this.voornaam = voornaam;
				this.role = role;
			}

			public String getLogin() {
				return login;
			}

			public void setLogin(String login) {
				this.login = login;
			}

			public String getNaam() {
				return naam;
			}

			public void setNaam(String naam) {
				this.naam = naam;
			}

			public String getPassword() {
				return password;
			}

			public void setPassword(String password) {
				this.password = password;
			}

			public String getTel() {
				return tel;
			}

			public void setTel(String tel) {
				this.tel = tel;
			}

			public String getVoornaam() {
				return voornaam;
			}

			public void setVoornaam(String voornaam) {
				this.voornaam = voornaam;
			}

			public int getRole() {
				return role;
			}

			public void setRole(int role) {
				this.role = role;
			}

		}

		public static class InnerGroep {

			private String bus, email, link, naam, plaats, postcode, straat;
			private int idgroep, huisnr;
			private List<String> takkenids;

			public InnerGroep() {

			}

			public InnerGroep(String bus, String email, String link, String naam, String plaats, String postcode,
					String straat, int idgroep, int huisnr, List<String> takkenids) {
				super();
				this.bus = bus;
				this.email = email;
				this.link = link;
				this.naam = naam;
				this.plaats = plaats;
				this.postcode = postcode;
				this.straat = straat;
				this.idgroep = idgroep;
				this.huisnr = huisnr;
				this.takkenids = takkenids;
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

			public String getNaam() {
				return naam;
			}

			public void setNaam(String naam) {
				this.naam = naam;
			}

			public String getPlaats() {
				return plaats;
			}

			public void setPlaats(String plaats) {
				this.plaats = plaats;
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

			public int getHuisnr() {
				return huisnr;
			}

			public void setHuisnr(int huisnr) {
				this.huisnr = huisnr;
			}

			public List<String> getTakkenids() {
				return takkenids;
			}

			public void setTakkenids(List<String> takkenids) {
				this.takkenids = takkenids;
			}

			public int getIdgroep() {
				return idgroep;
			}

			public void setIdgroep(int idgroep) {
				this.idgroep = idgroep;
			}

		}

		public static class InnerKind {

			private int idkind;
			private byte dafi, sport, zwemmen;
			@Temporal(TemporalType.DATE)
			private Date gebdatum;
			private String naam, opmerking, voornaam;
			private List<Dieet> dieeten;
			private List<InnerInnerMedicatie> medicaties;
			private List<Voogd> voogden;
			private List<Ziekte> ziektes;
			private List<String> takken; // id.tostring

			public InnerKind() {

			}

			public InnerKind(int idkind, byte dafi, byte sport, byte zwemmen, Date gebdatum, String naam,
					String opmerking, String voornaam, List<Dieet> dieeten, List<InnerInnerMedicatie> medicaties,
					List<Voogd> voogden, List<Ziekte> ziektes, List<String> takken) {
				this.idkind = idkind;
				this.dafi = dafi;
				this.sport = sport;
				this.zwemmen = zwemmen;
				this.gebdatum = gebdatum;
				this.naam = naam;
				this.opmerking = opmerking;
				this.voornaam = voornaam;
				this.dieeten = dieeten;
				this.medicaties = medicaties;
				this.voogden = voogden;
				this.ziektes = ziektes;
				this.takken = takken;
			}

			public int getIdkind() {
				return idkind;
			}

			public void setIdkind(int idkind) {
				this.idkind = idkind;
			}

			public byte getDafi() {
				return dafi;
			}

			public void setDafi(byte dafi) {
				this.dafi = dafi;
			}

			public byte getSport() {
				return sport;
			}

			public void setSport(byte sport) {
				this.sport = sport;
			}

			public byte getZwemmen() {
				return zwemmen;
			}

			public void setZwemmen(byte zwemmen) {
				this.zwemmen = zwemmen;
			}

			public Date getGebdatum() {
				return gebdatum;
			}

			public void setGebdatum(Date gebdatum) {
				this.gebdatum = gebdatum;
			}

			public String getNaam() {
				return naam;
			}

			public void setNaam(String naam) {
				this.naam = naam;
			}

			public String getOpmerking() {
				return opmerking;
			}

			public void setOpmerking(String opmerking) {
				this.opmerking = opmerking;
			}

			public String getVoornaam() {
				return voornaam;
			}

			public void setVoornaam(String voornaam) {
				this.voornaam = voornaam;
			}

			public List<Dieet> getDieeten() {
				return dieeten;
			}

			public void setDieeten(List<Dieet> dieeten) {
				this.dieeten = dieeten;
			}

			public List<InnerInnerMedicatie> getMedicaties() {
				return medicaties;
			}

			public void setMedicaties(List<InnerInnerMedicatie> medicaties) {
				this.medicaties = medicaties;
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

			public List<String> getTakken() {
				return takken;
			}

			public void setTakken(List<String> takken) {
				this.takken = takken;
			}

			public static class InnerInnerMedicatie {

				private int idmedicatie;
				private String naam, opmerking;
				private List<Tijdstip> tijdstippen;

				public InnerInnerMedicatie() {

				}

				public InnerInnerMedicatie(int idmedicatie, String naam, String opmerking, List<Tijdstip> tijdstippen) {

					this.idmedicatie = idmedicatie;
					this.naam = naam;
					this.opmerking = opmerking;
					this.tijdstippen = tijdstippen;
				}

				public int getIdmedicatie() {
					return idmedicatie;
				}

				public void setIdmedicatie(int idmedicatie) {
					this.idmedicatie = idmedicatie;
				}

				public String getNaam() {
					return naam;
				}

				public void setNaam(String naam) {
					this.naam = naam;
				}

				public String getOpmerking() {
					return opmerking;
				}

				public void setOpmerking(String opmerking) {
					this.opmerking = opmerking;
				}

				public List<Tijdstip> getTijdstippen() {
					return tijdstippen;
				}

				public void setTijdstippen(List<Tijdstip> tijdstippen) {
					this.tijdstippen = tijdstippen;
				}

			}
		}

		public static class InnerTak {

			private int idtak;
			private String naam, omschrijving, naamGroep;
			private List<String> kinderenids;

			public InnerTak() {

			}

			public InnerTak(int idtak, String naam, String omschrijving, List<String> kinderenids, String naamGroep) {
                this.naamGroep = naamGroep;
				this.idtak = idtak;
				this.naam = naam;
				this.omschrijving = omschrijving;
				this.kinderenids = kinderenids;
			}

			public int getIdtak() {
				return idtak;
			}

			public void setIdtak(int idtak) {
				this.idtak = idtak;
			}

			public String getNaam() {
				return naam;
			}

			public void setNaam(String naam) {
				this.naam = naam;
			}

			public String getOmschrijving() {
				return omschrijving;
			}

			public void setOmschrijving(String omschrijving) {
				this.omschrijving = omschrijving;
			}

			public List<String> getKinderenids() {
				return kinderenids;
			}

			public void setKinderenids(List<String> kinderenids) {
				this.kinderenids = kinderenids;
			}

			public String getNaamGroep() {
				return naamGroep;
			}

			public void setNaamGroep(String naamGroep) {
				this.naamGroep = naamGroep;
			}

		}
	}

}
