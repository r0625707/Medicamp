package com.medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the kind database table.
 * 
 */
@Entity
@Table(name="kind")
@NamedQuery(name="Kind.findAll", query="SELECT k FROM Kind k")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="idkind")
public class Kind implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private int idkind;

	@NotNull
	private byte dafi;

	@NotNull
	@Past(message = "Geboortedatum kan niet in de toekomst zijn")
	@Temporal(TemporalType.DATE)
	private Date gebdatum;

	@NotNull(message = "Vul een naam in")
	@Size(min = 1, message = "Vul een naam in")
	private String naam;

	@Lob
	private String opmerking;

	@NotNull
	private byte sport;

	@NotNull(message = "Vul een voornaam in")
	@Size(min = 1, message = "Vul een voornaam in")
	private String voornaam;

	@NotNull
	private byte zwemmen;

	//bi-directional many-to-one association to User
	@JsonBackReference("user-kind")
	@ManyToOne
	@JoinColumn(name="login", nullable=false)
	private User user;
	
	
	//bi-directional many-to-many association to Dieet
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="kind_dieet"
		, joinColumns={
			@JoinColumn(name="idkind")
			}
		, inverseJoinColumns={
			@JoinColumn(name="iddieet")
			}
		)
	private List<Dieet> dieeten;

	//bi-directional many-to-many association to Medicatie
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="kind_medicatie"
		, joinColumns={
			@JoinColumn(name="idkind")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idmedicatie")
			}
		)
	private List<Medicatie> medicaties;

	//bi-directional many-to-many association to Tak
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="kind_tak"
		, joinColumns={
			@JoinColumn(name="idkind")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idtak")
			}
		)
	private List<Tak> takken;

	//bi-directional many-to-many association to Voogd
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="kind_voogd"
		, joinColumns={
			@JoinColumn(name="idkind")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idvoogd")
			}
		)
	private List<Voogd> voogden;

	//bi-directional many-to-many association to Ziekte
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="kind_ziekte"
		, joinColumns={
			@JoinColumn(name="idkind")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idziekte")
			}
		)
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

	public List<Tak> getTakken() {
		return this.takken;
	}

	public void setTakken(List<Tak> takken) {
		this.takken = takken;
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