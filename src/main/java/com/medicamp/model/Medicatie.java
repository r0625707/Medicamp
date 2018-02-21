package com.medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the medicatie database table.
 * 
 */
@Entity
@Table(name="medicatie")
@NamedQuery(name="Medicatie.findAll", query="SELECT m FROM Medicatie m")
public class Medicatie implements Serializable {
	private static final long serialVersionUID = 1L;
	@GeneratedValue
	@Id
	private int idmedicatie;

	private String naam;

	@Lob
	private String opmerking;

	//bi-directional many-to-many association to Kind
	@ManyToMany(mappedBy="medicaties")
	private List<Kind> kinds;

	//bi-directional many-to-one association to Tijdstip
	@OneToMany(mappedBy="medicatie")
	private List<Tijdstip> tijdstips;

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

	public List<Kind> getKinds() {
		return this.kinds;
	}

	public void setKinds(List<Kind> kinds) {
		this.kinds = kinds;
	}

	public List<Tijdstip> getTijdstips() {
		return this.tijdstips;
	}

	public void setTijdstips(List<Tijdstip> tijdstips) {
		this.tijdstips = tijdstips;
	}

	public Tijdstip addTijdstip(Tijdstip tijdstip) {
		getTijdstips().add(tijdstip);
		tijdstip.setMedicatie(this);

		return tijdstip;
	}

	public Tijdstip removeTijdstip(Tijdstip tijdstip) {
		getTijdstips().remove(tijdstip);
		tijdstip.setMedicatie(null);

		return tijdstip;
	}

}