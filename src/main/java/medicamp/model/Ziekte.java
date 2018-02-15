package medicamp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown=true)
@Entity
public class Ziekte {
	@Id
	@GeneratedValue
	private long id;
public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

private String naam,symptomen,behandeling;
public Ziekte() {
	
}
public Ziekte(String naam, String symptomen, String behandeling) {

	this.naam = naam;
	this.symptomen = symptomen;
	this.behandeling = behandeling;
}

public String getNaam() {
	return naam;
}

public void setNaam(String naam) {
	this.naam = naam;
}

public String getSymptomen() {
	return symptomen;
}

public void setSymptomen(String symptomen) {
	this.symptomen = symptomen;
}

public String getBehandeling() {
	return behandeling;
}

public void setBehandeling(String behandeling) {
	this.behandeling = behandeling;
}

}
