package com.medicamp.mobiel;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the groep database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Groep implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private int idgroep;

	private String bus;

	
	private String email;

	private int huisnr;

	private String link;


	private String naam;


	private String plaats;

	
	private String postcode;


	private String straat;

	
	private User user;

	private List<String> takkenids;
	public List<String> getTakkenids() {
		return takkenids;
	}

	public void setTakkenids(List<String> takkenids) {
		this.takkenids = takkenids;
	}

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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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

}