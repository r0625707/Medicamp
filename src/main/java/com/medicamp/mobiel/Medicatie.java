package com.medicamp.mobiel;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the medicatie database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Medicatie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private int idmedicatie;


	private String naam;


	private String opmerking;

	
	private List<Kind> kinderen;

	
	private List<Tijdstip> tijdstippen;

	public Medicatie() {
	}

	public int getIdmedicatie() {
		return this.idmedicatie;
	}

	public void setIdmedicatie(int idmedicatie) {
		this.idmedicatie = idmedicatie;
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

	public List<Tijdstip> getTijdstippen() {
		return this.tijdstippen;
	}

	public void setTijdstippen(List<Tijdstip> tijdstippen) {
		this.tijdstippen = tijdstippen;
	}

	public Tijdstip addTijdstip(Tijdstip tijdstip) {
		getTijdstippen().add(tijdstip);
		tijdstip.setMedicatie(this);

		return tijdstip;
	}

	public Tijdstip removeTijdstip(Tijdstip tijdstip) {
		getTijdstippen().remove(tijdstip);
		tijdstip.setMedicatie(null);

		return tijdstip;
	}

}