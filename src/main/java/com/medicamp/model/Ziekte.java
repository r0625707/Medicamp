package com.medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ziekte database table.
 * 
 */
@Entity
@Table(name="ziekte")
@NamedQuery(name="Ziekte.findAll", query="SELECT z FROM Ziekte z")
public class Ziekte implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue
	@Id
	private int idziekte;

	@Lob
	private String behandeling;

	private String naam;

	@Lob
	private String symptomen;

	//bi-directional many-to-many association to Kind
	@ManyToMany(mappedBy="ziektes")
	private List<Kind> kinderen;

	public Ziekte() {
	}

	public int getIdziekte() {
		return this.idziekte;
	}

	public void setIdziekte(int idziekte) {
		this.idziekte = idziekte;
	}

	public String getBehandeling() {
		return this.behandeling;
	}

	public void setBehandeling(String behandeling) {
		this.behandeling = behandeling;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getSymptomen() {
		return this.symptomen;
	}

	public void setSymptomen(String symptomen) {
		this.symptomen = symptomen;
	}

	public List<Kind> getKinderen() {
		return this.kinderen;
	}

	public void setKinderen(List<Kind> kinderen) {
		this.kinderen = kinderen;
	}

}