package medicamp.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown=true)
@Entity
public class Behandeling {
	@Id
	@GeneratedValue
	private long id;
public long getId() {
		return id;
	}

private String naam,opmerking;
public Behandeling(){
	
}
public Behandeling(String naam, String opmerking) {
	
	this.naam = naam;
	this.opmerking = opmerking;
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
}
