package medicamp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown=true)

public class Behandeling {
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
