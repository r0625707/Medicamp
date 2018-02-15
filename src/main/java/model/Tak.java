package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown=true)
public class Tak {
private String naam, omschrijving;
public Tak() {
	
}
public Tak(String naam, String omschrijving) {
	setNaam(naam);
	setOmschrijving(omschrijving);
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
}
