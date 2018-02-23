package com.medicamp.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private String login;

	private String naam;
    
	@JsonIgnore
	private String password;

	private int role;
    
	@JsonIgnore
	private String salt;

	private String tel;

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

	public int getRole() {
		return this.role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getSalt() {
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

}