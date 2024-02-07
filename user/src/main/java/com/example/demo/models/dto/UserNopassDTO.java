package com.example.demo.models.dto;

import java.util.Date;

public class UserNopassDTO {
	private Long id;
	private String username;
	private String name;
	private String surname;
	private Date birthdate;
	private String email;
	
	public UserNopassDTO(Long id, String username, String name, String surname, Date birthdate, String email) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.email = email;
	}
	
	public UserNopassDTO() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
	
}
