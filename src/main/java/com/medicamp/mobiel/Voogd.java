package com.medicamp.mobiel;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the voogd database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Voogd implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private int idvoogd;

	private String bus;


	private int huisnr;

	
	private String naam;


	private String plaats;


	private String postcode;


	private String straat;


	private String tel;

	private String voornaam;

	
	private List<Kind> kinderen;

	
	private User user;

	public Voogd() {
	}

	public int getIdvoogd() {
		return this.idvoogd;
	}

	public void setIdvoogd(int idvoogd) {
		this.idvoogd = idvoogd;
	}

	public String getBus() {
		return this.bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	public int getHuisnr() {
		return this.huisnr;
	}

	public void setHuisnr(int huisnr) {
		this.huisnr = huisnr;
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

	public List<Kind> getKinderen() {
		return this.kinderen;
	}

	public void setKinderen(List<Kind> kinderen) {
		this.kinderen = kinderen;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}