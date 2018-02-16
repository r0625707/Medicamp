package medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the dieet database table.
 * 
 */
@Entity
@Table(name="dieet")
@NamedQuery(name="Dieet.findAll", query="SELECT d FROM Dieet d")
public class Dieet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idDieet;

	private String naam;

	@Lob
	private String opmerking;

	//bi-directional many-to-many association to Kind
	@ManyToMany(mappedBy="dieets")
	private List<Kind> kinds;

	public Dieet() {
	}

	public int getIdDieet() {
		return this.idDieet;
	}

	public void setIdDieet(int idDieet) {
		this.idDieet = idDieet;
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

}