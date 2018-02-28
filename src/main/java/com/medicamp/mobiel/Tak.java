package com.medicamp.mobiel;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;


/**
 * The persistent class for the tak database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class Tak implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private int idtak;


	private String naam;

	
	private String omschrijving;

	private List<String> kinderenids;
	public List<String> getKinderenids() {
		return kinderenids;
	}

	public void setKinderenids(List<String> kinderenids) {
		this.kinderenids = kinderenids;
	}

	private List<Activiteit> activiteiten;

	
	private List<Kind> kinderen;

	
	private Groep groep;

	
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