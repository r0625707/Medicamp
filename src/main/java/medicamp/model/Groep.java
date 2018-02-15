package medicamp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Groep {
	@Id
	@GeneratedValue
	private long id;

	public long getId() {
		return id;
	}

	private String naam, plaats, straat, link, password, email;
	private int postcode, huisnr, bus;

	public Groep() {

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

	public String getStraat() {
		return straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public int getHuisnr() {
		return huisnr;
	}

	public void setHuisnr(int huisnr) {
		this.huisnr = huisnr;
	}

	public int getBus() {
		return bus;
	}

	public void setBus(int bus) {
		this.bus = bus;
	}

	public Groep(String naam, String plaats, String straat, String link, String password, String email, int postcode,
			int huisnr, int bus) {
		super();
		this.naam = naam;
		this.plaats = plaats;
		this.straat = straat;
		this.link = link;
		this.password = password;
		this.email = email;
		this.postcode = postcode;
		this.huisnr = huisnr;
		this.bus = bus;
	}
}
