package medicamp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Dieet {
	@Id
	@GeneratedValue
	private long id;

	public long getId() {
		return id;
	}

	private String naam, omschrijving;

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

	public Dieet() {

	}

	public Dieet(String naam, String omschrijving) {

		this.naam = naam;
		this.omschrijving = omschrijving;
	}

}
