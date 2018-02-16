package medicamp.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the leiding database table.
 * 
 */
@Entity
@Table(name="leiding")
@NamedQuery(name="Leiding.findAll", query="SELECT l FROM Leiding l")
public class Leiding implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idLeiding;

	private String email;

	private String naam;

	private String tel;

	private String voornaam;

	//bi-directional many-to-one association to Groep
	@ManyToOne
	@JoinColumn(name="idGroep")
	private Groep groep;

	public Leiding() {
	}

	public int getIdLeiding() {
		return this.idLeiding;
	}

	public void setIdLeiding(int idLeiding) {
		this.idLeiding = idLeiding;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getVoornaam() {
		return this.voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public Groep getGroep() {
		return this.groep;
	}

	public void setGroep(Groep groep) {
		this.groep = groep;
	}

}