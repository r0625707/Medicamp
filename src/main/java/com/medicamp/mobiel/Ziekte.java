package com.medicamp.mobiel;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;



@JsonIgnoreProperties(ignoreUnknown = true)
public class Ziekte implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private int idziekte;

	
	private String behandeling;

	private String naam;

	
	private String symptomen;

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