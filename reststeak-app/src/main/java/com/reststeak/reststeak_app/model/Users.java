package com.reststeak.reststeak_app.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "users")
@XmlRootElement
public class Users {
	
	@Id
	@Column(name = "username")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "role")
	private String role;

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
