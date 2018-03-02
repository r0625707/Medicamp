package com.medicamp.sec;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="token")
@NamedQuery(name="token.findAll", query="SELECT t FROM Token t")
public class Token implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue
	@Id
	private int idtoken;

	
	private String token, login;


	@Temporal(TemporalType.TIMESTAMP)
	private Date expiration;
	
	public Token() {
		
		
	}


	public Token(int idtoken, String token, String login, Date expiration) {
		super();
		this.idtoken = idtoken;
		this.token = token;
		this.login = login;
		this.expiration = expiration;
	}


	public int getIdtoken() {
		return idtoken;
	}


	public void setIdtoken(int idtoken) {
		this.idtoken = idtoken;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public Date getExpiration() {
		return expiration;
	}


	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	
	

	

}