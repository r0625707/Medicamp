package medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the voogd database table.
 * 
 */
@Entity
@Table(name="voogd")
@NamedQuery(name="Voogd.findAll", query="SELECT v FROM Voogd v")
public class Voogd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idVoogd;

	private String altNaam;

	private String altTel;

	private String altVoornaam;

	private String bus;

	private String email;

	private int huisnr;

	@Lob
	private String link;

	private String naam;

	private String password;

	private String plaats;

	private String postcode;

	private String salt;

	private String straat;

	private String tel;

	private String voornaam;

	//bi-directional many-to-one association to Kind
	@OneToMany(mappedBy="voogd")
	private List<Kind> kinds;

	public Voogd() {
	}

	public int getIdVoogd() {
		return this.idVoogd;
	}

	public void setIdVoogd(int idVoogd) {
		this.idVoogd = idVoogd;
	}

	public String getAltNaam() {
		return this.altNaam;
	}

	public void setAltNaam(String altNaam) {
		this.altNaam = altNaam;
	}

	public String getAltTel() {
		return this.altTel;
	}

	public void setAltTel(String altTel) {
		this.altTel = altTel;
	}

	public String getAltVoornaam() {
		return this.altVoornaam;
	}

	public void setAltVoornaam(String altVoornaam) {
		this.altVoornaam = altVoornaam;
	}

	public String getBus() {
		return this.bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getHuisnr() {
		return this.huisnr;
	}

	public void setHuisnr(int huisnr) {
		this.huisnr = huisnr;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlaats() {
		return this.plaats;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getStraat() {
		return this.straat;
	}

	public void setStraat(String straat) {
		this.straat = straat;
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

	public List<Kind> getKinds() {
		return this.kinds;
	}

	public void setKinds(List<Kind> kinds) {
		this.kinds = kinds;
	}

	public Kind addKind(Kind kind) {
		getKinds().add(kind);
		kind.setVoogd(this);

		return kind;
	}

	public Kind removeKind(Kind kind) {
		getKinds().remove(kind);
		kind.setVoogd(null);

		return kind;
	}

}