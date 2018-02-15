package medicamp.model;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Tijdstip {
	@Id
	@GeneratedValue
	private long id;

	public long getId() {
		return id;
	}

	private String dosis;
	private Time tijdstip;

	public Tijdstip() {

	}

	public Tijdstip(String dosis, Time tijdstip) {

		this.dosis = dosis;
		this.tijdstip = tijdstip;
	}

	public String getDosis() {
		return dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public Time getTijdstip() {
		return tijdstip;
	}

	public void setTijdstip(Time tijdstip) {
		this.tijdstip = tijdstip;
	}
}
