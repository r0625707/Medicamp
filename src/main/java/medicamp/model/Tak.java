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
	private int idtak;

	private String naam;

	@Lob
	private String omschrijving;

	//bi-directional many-to-one association to Activiteit
	@OneToMany(mappedBy="tak")
	private List<Activiteit> activiteits;

	//bi-directional many-to-many association to Kind
	@ManyToMany(mappedBy="taks")
	private List<Kind> kinds;

	//bi-directional many-to-one association to Groep
	@ManyToOne
	private Groep groep;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="taks")
	private List<User> users;

	public Tak() {
	}

	public int getIdtak() {
		return this.idtak;
	}

	public void setIdtak(int idtak) {
		this.idtak = idtak;
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

	public List<Activiteit> getActiviteits() {
		return this.activiteits;
	}

	public void setActiviteits(List<Activiteit> activiteits) {
		this.activiteits = activiteits;
	}

	public Activiteit addActiviteit(Activiteit activiteit) {
		getActiviteits().add(activiteit);
		activiteit.setTak(this);

		return activiteit;
	}

	public Activiteit removeActiviteit(Activiteit activiteit) {
		getActiviteits().remove(activiteit);
		activiteit.setTak(null);

		return activiteit;
	}

	public List<Kind> getKinds() {
		return this.kinds;
	}

	public void setKinds(List<Kind> kinds) {
		this.kinds = kinds;
	}

	public Groep getGroep() {
		return this.groep;
	}

	public void setGroep(Groep groep) {
		this.groep = groep;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}