package com.medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dieet database table.
 * 
 */
@Entity
@Table(name="dieet")
@NamedQuery(name="Dieet.findAll", query="SELECT d FROM Dieet d")
public class Dieet implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue
	@Id
	private int iddieet;

	private String naam;

	@Lob
	private String opmerking;

	//bi-directional many-to-many association to Kind
	@ManyToMany(mappedBy="dieeten")
	private List<Kind> kinderen;

	public Dieet() {
	}

	public int getIddieet() {
		return this.iddieet;
	}

	public void setIddieet(int iddieet) {
		this.iddieet = iddieet;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getOpmerking() {
		return this.opmerking;
	}

	public void setOpmerking(String opmerking) {
		this.opmerking = opmerking;
	}

	public List<Kind> getKinderen() {
		return this.kinderen;
	}

	public void setKinderen(List<Kind> kinderen) {
		this.kinderen = kinderen;
	}

}