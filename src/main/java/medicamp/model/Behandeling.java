package medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the behandeling database table.
 * 
 */
@Entity
@Table(name="behandeling")
@NamedQuery(name="Behandeling.findAll", query="SELECT b FROM Behandeling b")
public class Behandeling implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idBehandeling;

	private String naam;

	@Lob
	private String opmerking;

	//bi-directional many-to-many association to Tijdstip
	@ManyToMany
	@JoinTable(
		name="behandeling_tijdstip"
		, joinColumns={
			@JoinColumn(name="idBehandeling")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idTijdstip")
			}
		)
	private List<Tijdstip> tijdstips;

	//bi-directional many-to-many association to Kind
	@ManyToMany(mappedBy="behandelings")
	private List<Kind> kinds;

	public Behandeling() {
	}

	public int getIdBehandeling() {
		return this.idBehandeling;
	}

	public void setIdBehandeling(int idBehandeling) {
		this.idBehandeling = idBehandeling;
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

	public List<Tijdstip> getTijdstips() {
		return this.tijdstips;
	}

	public void setTijdstips(List<Tijdstip> tijdstips) {
		this.tijdstips = tijdstips;
	}

	public List<Kind> getKinds() {
		return this.kinds;
	}

	public void setKinds(List<Kind> kinds) {
		this.kinds = kinds;
	}

}