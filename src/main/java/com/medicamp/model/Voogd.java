package com.medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the voogd database table.
 * 
 */
@Entity
@Table(name="voogd")
@NamedQuery(name="Voogd.findAll", query="SELECT v FROM Voogd v")
public class Voogd implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@GeneratedValue
	@Id
	private int idvoogd;

	private String bus;

	@NotNull(message = "Vul een huisnr in")
	@Size(min = 1, message = "Vul een huisnr in")
	private int huisnr;

	@NotNull(message = "Vul een naam in")
	@Size(min = 1, message = "Vul een naam in")
	private String naam;

	@NotNull(message = "Vul een plaatsnaam in")
	@Size(min = 1, message = "Vul een plaatsnaam in")
	private String plaats;

	@NotNull(message = "Vul een postcode in")
	@Size(min = 1, message = "Vul een postcode in")
	private String postcode;

	@NotNull(message = "Vul een straatnaam in")
	@Size(min = 1, message = "Vul een straatnaam in")
	private String straat;

	@NotNull(message = "Vul een telefoonnummer in")
	@Size(min = 1, message = "Vul een telefoonnummer in")
	private String tel;

	@NotNull(message = "Vul een voornaam in")
	@Size(min = 1, message = "Vul een voornaam in")
	private String voornaam;

	//bi-directional many-to-many association to Kind
	@JsonIgnore
	@ManyToMany(mappedBy="voogden")
	private List<Kind> kinderen;

	//bi-directional many-to-one association to User
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="login")
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