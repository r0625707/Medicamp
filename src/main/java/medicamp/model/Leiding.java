package medicamp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Leiding extends Persoon {
	@Id
	@GeneratedValue
	private long id;

	public long getId() {
		return id;
	}

	private String email, telefoonNummer;

	public Leiding() {

	}

	public Leiding(String naam, String voornaam, String email, String telefoonNummer) {
		super(naam, voornaam);

		this.email = email;
		this.telefoonNummer = telefoonNummer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefoonNummer() {
		return telefoonNummer;
	}

	public void setTelefoonNummer(String telefoonNummer) {
		this.telefoonNummer = telefoonNummer;
	}

}
