package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown=true)

public class Dieet {
private String naam,omschrijving;

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
