package com.medicamp.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.hash.Hashing;

import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull(message = "Gelieve een email-adres in te vullen")
	@Email(message = "Gelieve een geldig email-adres in te vullen")
	private String login;

	@NotNull(message = "Gelieve een naam in te vullen")
	@Size(min = 1, max = 255, message = "Gelieve een naam van geldige lengte in te vullen")
	private String naam;
    
	@NotNull(message = "Gelieve een wachtwoord in te vullen")
	@Size(min = 1, max = 255, message = "Gelieve een wachtwoord van geldige lengte in te vullen")
	private String password;

	private int role;
    
	@JsonIgnore
	private String salt;

	@Pattern(regexp = "^[0-9+/ ]*", message = "Gelieve een telefoonnummer van geldige lengte in te vullen")
	private String tel;

	@NotNull(message = "Gelieve een voornaam in te vullen")
	@Size(min = 1, max = 255, message = "Gelieve een voornaam van geldige lengte in te vullen")
	private String voornaam;

	//bi-directional many-to-one association to Groep
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Groep> groepen;

	//bi-directional many-to-one association to Kind
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Kind> kinderen;

	//bi-directional many-to-many association to Tak
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="user_tak"
		, joinColumns={
			@JoinColumn(name="login")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idtak")
			}
		)
	private List<Tak> takken;

	//bi-directional many-to-one association to Voogd
	
	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Voogd> voogden;

	public User() {
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setPasswordHashed(String password) {
		setPassword(hashPassword(password));
	}

	public int getRole() {
		return this.role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getSalt() {
		if(this.salt==null) {
			setSalt(generateSalt());
		}
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getVoornaam() {
		return this.voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public List<Groep> getGroepen() {
		return this.groepen;
	}

	public void setGroepen(List<Groep> groepen) {
		this.groepen = groepen;
	}

	public Groep addGroep(Groep groep) {
		getGroepen().add(groep);
		groep.setUser(this);

		return groep;
	}

	public Groep removeGroep(Groep groep) {
		getGroepen().remove(groep);
		groep.setUser(null);

		return groep;
	}

	public List<Kind> getKinderen() {
		return this.kinderen;
	}

	public void setKinderen(List<Kind> kinderen) {
		this.kinderen = kinderen;
	}

	public Kind addKind(Kind kind) {
		getKinderen().add(kind);
		kind.setUser(this);

		return kind;
	}

	public Kind removeKind(Kind kind) {
		getKinderen().remove(kind);
		kind.setUser(null);

		return kind;
	}

	public List<Tak> getTakken() {
		return this.takken;
	}

	public void setTakken(List<Tak> takken) {
		this.takken = takken;
	}

	public List<Voogd> getVoogden() {
		return this.voogden;
	}

	public void setVoogden(List<Voogd> voogden) {
		this.voogden = voogden;
	}

	public Voogd addVoogd(Voogd voogd) {
		getVoogden().add(voogd);
		voogd.setUser(this);

		return voogd;
	}

	public Voogd removeVoogd(Voogd voogd) {
		getVoogden().remove(voogd);
		voogd.setUser(null);

		return voogd;
	}
	
	private String hashPassword(String password) {
		String sha256hex = Hashing.sha256().hashString(password.concat(getSalt()), StandardCharsets.UTF_8).toString();
		return sha256hex;
	}
	
	private String generateSalt() {
		SecureRandom random = new SecureRandom();
		byte seed[] = random.generateSeed(20);
		return new BigInteger(1, seed).toString(16);
	}
	
	public boolean isCorrectPassword(String password) {
		String hashed = hashPassword(password);
		return getPassword().equals(hashed);
	}

}