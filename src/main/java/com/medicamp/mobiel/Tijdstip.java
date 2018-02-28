package com.medicamp.mobiel;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;


/**
 * The persistent class for the tijdstip database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Tijdstip implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private int idtijdstip;

	private String dosis;

	
	private Date tijdstip;

	
	private Medicatie medicatie;

	public Tijdstip() {
	}

	public int getIdtijdstip() {
		return this.idtijdstip;
	}

	public void setIdtijdstip(int idtijdstip) {
		this.idtijdstip = idtijdstip;
	}

	public String getDosis() {
		return this.dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public Date getTijdstip() {
		return this.tijdstip;
	}

	public void setTijdstip(Date tijdstip) {
		this.tijdstip = tijdstip;
	}

	public Medicatie getMedicatie() {
		return this.medicatie;
	}

	public void setMedicatie(Medicatie medicatie) {
		this.medicatie = medicatie;
	}

}