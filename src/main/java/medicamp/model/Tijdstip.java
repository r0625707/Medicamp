package medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the tijdstip database table.
 * 
 */
@Entity
@Table(name="tijdstip")
@NamedQuery(name="Tijdstip.findAll", query="SELECT t FROM Tijdstip t")
public class Tijdstip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idTijdstip;

	private String dosis;

	private Time tijdstip;

	//bi-directional many-to-many association to Behandeling
	@ManyToMany(mappedBy="tijdstips")
	private List<Behandeling> behandelings;

	public Tijdstip() {
	}

	public int getIdTijdstip() {
		return this.idTijdstip;
	}

	public void setIdTijdstip(int idTijdstip) {
		this.idTijdstip = idTijdstip;
	}

	public String getDosis() {
		return this.dosis;
	}

	public void setDosis(String dosis) {
		this.dosis = dosis;
	}

	public Time getTijdstip() {
		return this.tijdstip;
	}

	public void setTijdstip(Time tijdstip) {
		this.tijdstip = tijdstip;
	}

	public List<Behandeling> getBehandelings() {
		return this.behandelings;
	}

	public void setBehandelings(List<Behandeling> behandelings) {
		this.behandelings = behandelings;
	}

}