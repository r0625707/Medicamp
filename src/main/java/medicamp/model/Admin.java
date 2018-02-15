package medicamp.model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties (ignoreUnknown=true)
@Entity
public class Admin extends Persoon{
	@Id
	@GeneratedValue
	private long id;

private String email,password,salt;
public long getId() {
	return id;
}
public Admin() {
	
}
public Admin (String naam, String voornaam, String email, String password, String salt) {
	
	super(naam,voornaam);
	this.email = email;
	this.password = password;
	this.salt = salt;
}



public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getSalt() {
	return salt;
}

public void setSalt(String salt) {
	this.salt = salt;
}
}
