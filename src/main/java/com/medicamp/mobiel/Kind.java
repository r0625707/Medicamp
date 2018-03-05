package com.medicamp.mobiel;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the kind database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Kind implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private int idkind;

	
	private byte dafi;

	@Temporal(TemporalType.TIMESTAMP)
	private Date gebdatum;


	private String naam;


	private String opmerking;

	
	private byte sport;

	private String voornaam;

	
	private byte zwemmen;

	
	private User user;

	
	
	private List<Dieet> dieeten;

	
	private List<Medicatie> medicaties;

	
	private List<String> takkenids=new ArrayList<String>();
public void addTakId(String idtak) {
	takkenids.add(idtak);
}
	//bi-directional many-to-many association to Voogd

	private List<Voogd> voogden;

	
	private List<Ziekte> ziektes;

	public Kind() {
	}

	public int getIdkind() {
		return this.idkind;
	}

	public void setIdkind(int idkind) {
		this.idkind = idkind;
	}

	public byte getDafi() {
		return this.dafi;
	}

	public void setDafi(byte dafi) {
		this.dafi = dafi;
	}

	public Date getGebdatum() {
		return this.gebdatum;
	}

	public void setGebdatum(Date gebdatum) {
		this.gebdatum = gebdatum;
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

	public byte getSport() {
		return this.sport;
	}

	public void setSport(byte sport) {
		this.sport = sport;
	}

	public String getVoornaam() {
		return this.voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public byte getZwemmen() {
		return this.zwemmen;
	}

	public void setZwemmen(byte zwemmen) {
		this.zwemmen = zwemmen;
	}

	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Dieet> getDieeten() {
		return this.dieeten;
	}

	public void setDieeten(List<Dieet> dieeten) {
		this.dieeten = dieeten;
	}

	public List<Medicatie> getMedicaties() {
		return this.medicaties;
	}

	public void setMedicaties(List<Medicatie> medicaties) {
		this.medicaties = medicaties;
	}

	public List<String> getTakken() {
		return this.takkenids;
	}

	public void setTakken(List<String> takkenids) {
		this.takkenids = takkenids;
	}

	public List<Voogd> getVoogden() {
		return this.voogden;
	}

	public void setVoogden(List<Voogd> voogden) {
		this.voogden = voogden;
	}

	public List<Ziekte> getZiektes() {
		return this.ziektes;
	}

	public void setZiektes(List<Ziekte> ziektes) {
		this.ziektes = ziektes;
	}

}