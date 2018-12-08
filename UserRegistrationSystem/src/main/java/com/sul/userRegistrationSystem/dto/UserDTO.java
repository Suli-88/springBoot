package com.sul.userRegistrationSystem.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table
public class UserDTO {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Long id;
	
	@NotEmpty
	@Length(max = 50)
	@Column(name = "NAME")
	private String name;

	@NotEmpty
	@Length(max = 100)
	@Column(name = "ADDRESS")
	private String address;
	
	@Length(max = 100)
	@Email
	@Column(name = "EMAIL")
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
