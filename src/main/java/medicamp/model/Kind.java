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
	private int idkind;

	private byte dafi;

	@Temporal(TemporalType.DATE)
	private Date gebdatum;

	private byte meldingen;

	private String naam;

	@Lob
	private String opmerking;

	private byte sport;

	private String voornaam;

	private byte zwemmen;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="login")
	private User user;

	//bi-directional many-to-many association to Dieet
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
	private List<Dieet> dieets;

	//bi-directional many-to-many association to Medicatie
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
	private List<Tak> taks;

	//bi-directional many-to-many association to Voogd
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
	private List<Voogd> voogds;

	//bi-directional many-to-many association to Ziekte
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

	public byte getMeldingen() {
		return this.meldingen;
	}

	public void setMeldingen(byte meldingen) {
		this.meldingen = meldingen;
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

	public List<Dieet> getDieets() {
		return this.dieets;
	}

	public void setDieets(List<Dieet> dieets) {
		this.dieets = dieets;
	}

	public List<Medicatie> getMedicaties() {
		return this.medicaties;
	}

	public void setMedicaties(List<Medicatie> medicaties) {
		this.medicaties = medicaties;
	}

	public List<Tak> getTaks() {
		return this.taks;
	}

	public void setTaks(List<Tak> taks) {
		this.taks = taks;
	}

	public List<Voogd> getVoogds() {
		return this.voogds;
	}

	public void setVoogds(List<Voogd> voogds) {
		this.voogds = voogds;
	}

	public List<Ziekte> getZiektes() {
		return this.ziektes;
	}

	public void setZiektes(List<Ziekte> ziektes) {
		this.ziektes = ziektes;
	}

}