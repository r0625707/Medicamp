package model;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown=true)

public class Tijdstip {
private String dosis;
private Time tijdstip;
public Tijdstip(){
	
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
