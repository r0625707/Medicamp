package medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the kind database table.
 * 
 */
@Entity
@Table(name="kind")
@NamedQuery(name="Kind.findAll", query="SELECT k FROM Kind k")
public class Kind implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idKind;

	private byte dafi;

	@Temporal(TemporalType.DATE)
	private Date gebDatum;

	private String naam;

	@Lob
	private String opmerking;

	private byte sport;

	private String voornaam;

	private byte zwemmen;

	//bi-directional many-to-many association to Activiteit
	@ManyToMany
	@JoinTable(
		name="kind_activiteit"
		, joinColumns={
			@JoinColumn(name="idKind")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idActiviteit")
			}
		)
	private List<Activiteit> activiteits;

	//bi-directional many-to-many association to Behandeling
	@ManyToMany
	@JoinTable(
		name="kind_behandeling"
		, joinColumns={
			@JoinColumn(name="idKind")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idBehandeling")
			}
		)
	private List<Behandeling> behandelings;

	//bi-directional many-to-many association to Dieet
	@ManyToMany
	@JoinTable(
		name="kind_has_dieet"
		, joinColumns={
			@JoinColumn(name="idKind")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idDieet")
			}
		)
	private List<Dieet> dieets;

	//bi-directional many-to-many association to Tak
	@ManyToMany
	@JoinTable(
		name="kind_tak"
		, joinColumns={
			@JoinColumn(name="idKind")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idTak")
			}
		)
	private List<Tak> taks;

	//bi-directional many-to-one association to Voogd
	@ManyToOne
	@JoinColumn(name="idVoogd")
	private Voogd voogd;

	//bi-directional many-to-many association to Ziekte
	@ManyToMany
	@JoinTable(
		name="kind_ziekte"
		, joinColumns={
			@JoinColumn(name="idKind")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idZiekte")
			}
		)
	private List<Ziekte> ziektes;

	public Kind() {
	}

	public int getIdKind() {
		return this.idKind;
	}

	public void setIdKind(int idKind) {
		this.idKind = idKind;
	}

	public byte getDafi() {
		return this.dafi;
	}

	public void setDafi(byte dafi) {
		this.dafi = dafi;
	}

	public Date getGebDatum() {
		return this.gebDatum;
	}

	public void setGebDatum(Date gebDatum) {
		this.gebDatum = gebDatum;
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

	public List<Activiteit> getActiviteits() {
		return this.activiteits;
	}

	public void setActiviteits(List<Activiteit> activiteits) {
		this.activiteits = activiteits;
	}

	public List<Behandeling> getBehandelings() {
		return this.behandelings;
	}

	public void setBehandelings(List<Behandeling> behandelings) {
		this.behandelings = behandelings;
	}

	public List<Dieet> getDieets() {
		return this.dieets;
	}

	public void setDieets(List<Dieet> dieets) {
		this.dieets = dieets;
	}

	public List<Tak> getTaks() {
		return this.taks;
	}

	public void setTaks(List<Tak> taks) {
		this.taks = taks;
	}

	public Voogd getVoogd() {
		return this.voogd;
	}

	public void setVoogd(Voogd voogd) {
		this.voogd = voogd;
	}

	public List<Ziekte> getZiektes() {
		return this.ziektes;
	}

	public void setZiektes(List<Ziekte> ziektes) {
		this.ziektes = ziektes;
	}

}