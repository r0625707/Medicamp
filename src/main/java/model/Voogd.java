package model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown=true)

public class Voogd extends Persoon {
private String naam,voornaam, telefoonNummer,email,plaats,straat,passwoord,salt;
private int postcode,huisNummer,bus;
private List<Voogd> altVoogdList;
public Voogd(String naam, String voornaam, String telefoonNummer, String email, String plaats, String straat,
		int postcode, int huisNummer, int bus) {
	super(naam,voornaam);
	//this.naam = naam;
	//this.voornaam = voornaam;
	this.telefoonNummer = telefoonNummer;
	this.email = email;
	this.plaats = plaats;
	this.straat = straat;
	this.postcode = postcode;
	this.huisNummer = huisNummer;
	this.bus = bus;
}
public Voogd(String naam, String voornaam, String telefoonNummer, String email, String plaats, String straat,
		String passwoord, String salt, int postcode, int huisNummer, int bus, List<Voogd> altVoogdList) {
	super(naam,voornaam);
	//this.naam = naam;
	//this.voornaam = voornaam;
	this.telefoonNummer = telefoonNummer;
	this.email = email;
	this.plaats = plaats;
	this.straat = straat;
	this.passwoord = passwoord;
	this.salt = salt;
	this.postcode = postcode;
	this.huisNummer = huisNummer;
	this.bus = bus;
	//eerst voogd in list zetten en dan meegeven 
	//OF
	//dit aanpassen
	this.altVoogdList = altVoogdList;
}
public Voogd() {
	this.altVoogdList=new ArrayList<Voogd>();
}

public String getTelefoonNummer() {
	return telefoonNummer;
}
public void setTelefoonNummer(String telefoonNummer) {
	this.telefoonNummer = telefoonNummer;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPlaats() {
	return plaats;
}
public void setPlaats(String plaats) {
	this.plaats = plaats;
}
public String getStraat() {
	return straat;
}
public void setStraat(String straat) {
	this.straat = straat;
}
public String getPasswoord() {
	return passwoord;
}
public void setPasswoord(String passwoord) {
	this.passwoord = passwoord;
}
public String getSalt() {
	return salt;
}
public void setSalt(String salt) {
	this.salt = salt;
}
public int getPostcode() {
	return postcode;
}
public void setPostcode(int postcode) {
	this.postcode = postcode;
}
public int getHuisNummer() {
	return huisNummer;
}
public void setHuisNummer(int huisNummer) {
	this.huisNummer = huisNummer;
}
public int getBus() {
	return bus;
}
public void setBus(int bus) {
	this.bus = bus;
}
public List<Voogd> getAltVoogdList() {
	return altVoogdList;
}
public void addAltVoogd(Voogd altVoogd) {
	altVoogdList.add(altVoogd);
}


//public String getNaam() {
//return naam;
//}
//public void setNaam(String naam) {
//this.naam = naam;
//}
//public String getVoornaam() {
//return voornaam;
//}
//public void setVoornaam(String voornaam) {
//this.voornaam = voornaam;
//}
}
