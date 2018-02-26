package com.medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;


/**
 * The persistent class for the activiteit database table.
 * 
 */
@Entity
@Table(name="activiteit")
@NamedQuery(name="Activiteit.findAll", query="SELECT a FROM Activiteit a")
public class Activiteit implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue
	@Id
	private int idactiviteit;
	
	@NotNull(message = "Vul een begindatum in")
	@Temporal(TemporalType.TIMESTAMP)
	private Date begindatum;

	@NotNull(message = "Vul een einddatum in")
	@Future(message = "Einddatum kan niet in het verleden liggen")
	@Temporal(TemporalType.TIMESTAMP)
	private Date einddatum;

	//bi-directional many-to-one association to Tak
	@JsonIgnore
	@NotNull(message = "Vermeld een tak")
	@ManyToOne
	@JoinColumn(name="idtak")
	private Tak tak;

	public Activiteit() {
	}

	public int getIdactiviteit() {
		return this.idactiviteit;
	}

	public void setIdactiviteit(int idactiviteit) {
		this.idactiviteit = idactiviteit;
	}

	public Date getBegindatum() {
		return this.begindatum;
	}

	public void setBegindatum(Date begindatum) {
		this.begindatum = begindatum;
	}

	public Date getEinddatum() {
		return this.einddatum;
	}

	public void setEinddatum(Date einddatum) {
		this.einddatum = einddatum;
	}

	public Tak getTak() {
		return this.tak;
	}

	public void setTak(Tak tak) {
		this.tak = tak;
	}

}