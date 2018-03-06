package com.medicamp.mobiel;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Activiteit implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private int idactiviteit;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date begindatum;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
	private Date einddatum;

	//bi-directional many-to-one association to Tak
	
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