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

	@Id
	private int idActiviteit;

	@Temporal(TemporalType.DATE)
	private Date beginDatum;

	@Lob
	private String beschrijving;

	@Temporal(TemporalType.DATE)
	private Date eindDatum;

	private String naam;

	//bi-directional many-to-many association to Kind
	@ManyToMany(mappedBy="activiteits")
	private List<Kind> kinds;

	//bi-directional many-to-many association to Tak
	@ManyToMany(mappedBy="activiteits")
	private List<Tak> taks;

	public Activiteit() {
	}

	public int getIdActiviteit() {
		return this.idActiviteit;
	}

	public void setIdActiviteit(int idActiviteit) {
		this.idActiviteit = idActiviteit;
	}

	public Date getBeginDatum() {
		return this.beginDatum;
	}

	public void setBeginDatum(Date beginDatum) {
		this.beginDatum = beginDatum;
	}

	public String getBeschrijving() {
		return this.beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public Date getEindDatum() {
		return this.eindDatum;
	}

	public void setEindDatum(Date eindDatum) {
		this.eindDatum = eindDatum;
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