package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown=true)
public class Activiteit {
	public Activiteit() {
		
	}
public Activiteit(String naam, String beschrijving, boolean beginDatum, boolean eindDatum) {
		this.naam = naam;
		this.beschrijving = beschrijving;
		this.beginDatum = beginDatum;
		this.eindDatum = eindDatum;
	}
private String naam,beschrijving;
private boolean beginDatum,eindDatum;
public String getNaam() {
	return naam;
}
public void setNaam(String naam) {
	this.naam = naam;
}
public String getBeschrijving() {
	return beschrijving;
}
public void setBeschrijving(String beschrijving) {
	this.beschrijving = beschrijving;
}
public boolean isBeginDatum() {
	return beginDatum;
}
public void setBeginDatum(boolean beginDatum) {
	this.beginDatum = beginDatum;
}
public boolean isEindDatum() {
	return eindDatum;
}
public void setEindDatum(boolean eindDatum) {
	this.eindDatum = eindDatum;
}
}
