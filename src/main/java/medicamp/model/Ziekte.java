package medicamp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown=true)

public class Ziekte {
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
