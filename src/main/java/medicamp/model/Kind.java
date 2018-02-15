package medicamp.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties (ignoreUnknown=true)
@Entity
public class Kind extends Persoon{
	@Id
	@GeneratedValue
	private long id;
private String opmerking;
private Date geboorteDatum;

public long getId() {
	return id;
}

public Kind() {
	
}

public Kind(String naam, String voornaam, String opmerking, Date geboorteDatum, Boolean zwemmen, Boolean sport,
		Boolean dafalgan) {
	super(naam,voornaam);
	this.opmerking = opmerking;
	this.geboorteDatum = geboorteDatum;
	this.zwemmen = zwemmen;
	this.sport = sport;
	this.dafalgan = dafalgan;
}

public String getOpmerking() {
	return opmerking;
}
public void setOpmerking(String opmerking) {
	this.opmerking = opmerking;
}
public Date getGeboorteDatum() {
	return geboorteDatum;
}
public void setGeboorteDatum(Date geboorteDatum) {
	this.geboorteDatum = geboorteDatum;
}
public Boolean getZwemmen() {
	return zwemmen;
}
public void setZwemmen(Boolean zwemmen) {
	this.zwemmen = zwemmen;
}
public Boolean getSport() {
	return sport;
}
public void setSport(Boolean sport) {
	this.sport = sport;
}
public Boolean getDafalgan() {
	return dafalgan;
}
public void setDafalgan(Boolean dafalgan) {
	this.dafalgan = dafalgan;
}
private Boolean zwemmen, sport,dafalgan;
}
