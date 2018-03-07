package com.medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;


/**
 * The persistent class for the groep database table.
 * 
 */
@Entity
@Table(name="groep")
@NamedQuery(name="Groep.findAll", query="SELECT g FROM Groep g")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idgroep")
public class Groep implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue
	@Id
	private int idgroep;
	@Lob
	private String bus;

	@NotNull(message = "Vul een email-adres in")
	@Email
	private String email;

	@NotNull(message = "Vul een huisnr in")
	private int huisnr;
	@Lob
	private String link;

	@NotNull(message = "Vul een naam in")
	@Size(min = 1, message = "Vul een naam in")
	private String naam;

	@NotNull(message = "Vul een plaatsnaam in")
	@Size(min = 1, message = "Vul een plaatsnaam in")
	private String plaats;

	@NotNull(message = "Vul een postcode in")
	@Size(min = 1, message = "Vul een postcode in")
	private String postcode;

	@NotNull(message = "Vule een straatnaam in")
	@Size(min = 1, message = "Vul een straatnaam in")
	private String straat;

	//bi-directional many-to-one association to User
	@JsonIgnore
	@ManyToMany(mappedBy="groepen")
	private List<User> users;

	//bi-directional many-to-one association to Tak
	@JsonIgnore
	@OneToMany(mappedBy="groep")
	private List<Tak> takken;

	public Groep() {
	}

	public int getIdgroep() {
		return this.idgroep;
	}

	public void setIdgroep(int idgroep) {
		this.idgroep = idgroep;
	}

	public String getBus() {
		return this.bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getHuisnr() {
		return this.huisnr;
	}

	public void setHuisnr(int huisnr) {
		this.huisnr = huisnr;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getPlaats() {
		return this.plaats;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getStraat() {
		return this.straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Tak> getTakken() {
		return this.takken;
	}

	public void setTakken(List<Tak> takken) {
		this.takken = takken;
	}

	public Tak addTak(Tak tak) {
		getTakken().add(tak);
		tak.setGroep(this);

		return tak;
	}

	public Tak removeTak(Tak tak) {
		getTakken().remove(tak);
		tak.setGroep(null);

		return tak;
	}

	public void addUser(User user) {
		users.add(user);
		user.addGroep(this);
		
	}

}