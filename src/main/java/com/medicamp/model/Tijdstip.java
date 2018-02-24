package com.medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the tijdstip database table.
 * 
 */
@Entity
@Table(name="tijdstip")
@NamedQuery(name="Tijdstip.findAll", query="SELECT t FROM Tijdstip t")
public class Tijdstip implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@GeneratedValue
	@Id
	private int idtijdstip;

	private String dosis;

	@NotNull(message = "Vul een tijdstip in")
	@Future(message = "Tijdstip mag niet in de toekomst zijn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tijdstip;

	//bi-directional many-to-one association to Medicatie
	@NotNull
	@ManyToOne
	@JoinColumn(name="idmedicatie")
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