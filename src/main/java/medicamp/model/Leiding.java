package medicamp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown=true)

public class Leiding extends Persoon {
private String naam,voornaam,email,telefoonNummer;

public Leiding() {
	
}

public Leiding(String naam, String voornaam, String email, String telefoonNummer) {
	super(naam,voornaam);
	
	this.email = email;
	this.telefoonNummer = telefoonNummer;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getTelefoonNummer() {
	return telefoonNummer;
}

public void setTelefoonNummer(String telefoonNummer) {
	this.telefoonNummer = telefoonNummer;
}



}
