package com.example.demo.models.dto;

import java.util.Date;

public class UserRegisterDTO {
	private String username;
	private String password;
	private String name;
	private String surname;
	private Date birthdate;
	private String email;
	
	public UserRegisterDTO(String username, String password, String name, String surname, Date birthdate, String email) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.email = email;
		this.password=password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
