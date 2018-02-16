package medicamp.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String login;

	private String naam;

	private String password;

	private int role;

	private String salt;

	private String tel;

	private String voornaam;

	//bi-directional many-to-one association to Groep
	@OneToMany(mappedBy="user")
	private List<Groep> groeps;

	//bi-directional many-to-one association to Kind
	@OneToMany(mappedBy="user")
	private List<Kind> kinds;

	//bi-directional many-to-many association to Tak
	@ManyToMany
	@JoinTable(
		name="user_tak"
		, joinColumns={
			@JoinColumn(name="login")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idtak")
			}
		)
	private List<Tak> taks;

	public User() {
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
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

	public int getRole() {
		return this.role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public List<Groep> getGroeps() {
		return this.groeps;
	}

	public void setGroeps(List<Groep> groeps) {
		this.groeps = groeps;
	}

	public Groep addGroep(Groep groep) {
		getGroeps().add(groep);
		groep.setUser(this);

		return groep;
	}

	public Groep removeGroep(Groep groep) {
		getGroeps().remove(groep);
		groep.setUser(null);

		return groep;
	}

	public List<Kind> getKinds() {
		return this.kinds;
	}

	public void setKinds(List<Kind> kinds) {
		this.kinds = kinds;
	}

	public Kind addKind(Kind kind) {
		getKinds().add(kind);
		kind.setUser(this);

		return kind;
	}

	public Kind removeKind(Kind kind) {
		getKinds().remove(kind);
		kind.setUser(null);

		return kind;
	}

	public List<Tak> getTaks() {
		return this.taks;
	}

	public void setTaks(List<Tak> taks) {
		this.taks = taks;
	}

}