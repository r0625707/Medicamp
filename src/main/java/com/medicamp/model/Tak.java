package com.medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the tak database table.
 * 
 */
@Entity
@Table(name="tak")
@NamedQuery(name="Tak.findAll", query="SELECT t FROM Tak t")
public class Tak implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@GeneratedValue
	@Id
	private int idtak;

	@NotNull(message = "Vul een naam in")
	@Size(min = 1, message = "Vul een naam in")
	private String naam;

	@Lob
	private String omschrijving;

	//bi-directional many-to-one association to Activiteit
	@JsonIgnore
	@OneToMany(mappedBy="tak")
	private List<Activiteit> activiteiten;

	//bi-directional many-to-many association to Kind
	@JsonIgnore
	@ManyToMany(mappedBy="takken")
	private List<Kind> kinderen;

	//bi-directional many-to-one association to Groep
	@NotNull
	@ManyToOne
	private Groep groep;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="takken")
	private List<User> users;

	public Tak() {
	}

	public int getIdtak() {
		return this.idtak;
	}

	public void setIdtak(int idtak) {
		this.idtak = idtak;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getOmschrijving() {
		return this.omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public List<Activiteit> getActiviteiten() {
		return this.activiteiten;
	}

	public void setActiviteiten(List<Activiteit> activiteiten) {
		this.activiteiten = activiteiten;
	}

	public Activiteit addActiviteit(Activiteit activiteit) {
		getActiviteiten().add(activiteit);
		activiteit.setTak(this);

		return activiteit;
	}

	public Activiteit removeActiviteit(Activiteit activiteit) {
		getActiviteiten().remove(activiteit);
		activiteit.setTak(null);

		return activiteit;
	}

	public List<Kind> getKinderen() {
		return this.kinderen;
	}

	public void setKinderen(List<Kind> kinderen) {
		this.kinderen = kinderen;
	}

	public Groep getGroep() {
		return this.groep;
	}

	public void setGroep(Groep groep) {
		this.groep = groep;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}