package medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

	@Temporal(TemporalType.DATE)
	private Date begindatum;

	@Lob
	private String beschrijving;

	@Temporal(TemporalType.DATE)
	private Date einddatum;

	private String naam;

	//bi-directional many-to-many association to Kind
	@ManyToMany
	@JoinTable(
		name="activiteit_kind"
		, joinColumns={
			@JoinColumn(name="idactiviteit")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idkind")
			}
		)
	private List<Kind> kinds;

	//bi-directional many-to-many association to Tak
	@ManyToMany(mappedBy="activiteits")
	private List<Tak> taks;

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

	public String getBeschrijving() {
		return this.beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public Date getEinddatum() {
		return this.einddatum;
	}

	public void setEinddatum(Date einddatum) {
		this.einddatum = einddatum;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public List<Kind> getKinds() {
		return this.kinds;
	}

	public void setKinds(List<Kind> kinds) {
		this.kinds = kinds;
	}

	public List<Tak> getTaks() {
		return this.taks;
	}

	public void setTaks(List<Tak> taks) {
		this.taks = taks;
	}

}