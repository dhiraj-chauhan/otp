package com.empower.product.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {
	
	@Id
	private String username;
	private String password;
	private Integer pin;
	
	public Integer getPin() {
		return pin;
	}
	public void setPin(Integer pin) {
		this.pin = pin;
	}
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	


}
