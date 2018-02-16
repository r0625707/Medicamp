package medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tak database table.
 * 
 */
@Entity
@Table(name="tak")
@NamedQuery(name="Tak.findAll", query="SELECT t FROM Tak t")
public class Tak implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idTak;

	private String naam;

	@Lob
	private String omschrijving;

	//bi-directional many-to-many association to Kind
	@ManyToMany(mappedBy="taks")
	private List<Kind> kinds;

	//bi-directional many-to-many association to Activiteit
	@ManyToMany
	@JoinTable(
		name="tak_activiteit"
		, joinColumns={
			@JoinColumn(name="idTak")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idActiviteit")
			}
		)
	private List<Activiteit> activiteits;

	//bi-directional many-to-one association to Groep
	@ManyToOne
	@JoinColumn(name="idGroep")
	private Groep groep;

	public Tak() {
	}

	public int getIdTak() {
		return this.idTak;
	}

	public void setIdTak(int idTak) {
		this.idTak = idTak;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getOmschrijving() {
		return this.omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public List<Kind> getKinds() {
		return this.kinds;
	}

	public void setKinds(List<Kind> kinds) {
		this.kinds = kinds;
	}

	public List<Activiteit> getActiviteits() {
		return this.activiteits;
	}

	public void setActiviteits(List<Activiteit> activiteits) {
		this.activiteits = activiteits;
	}

	public Groep getGroep() {
		return this.groep;
	}

	public void setGroep(Groep groep) {
		this.groep = groep;
	}

}