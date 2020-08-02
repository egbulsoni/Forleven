package com.classroom.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long alumniCode;
	
	@NotBlank
	@Size(min = 4)
	private String name;
	
	@NotBlank
	@Size(min = 4)
	private String surname;
	
//	private ArrayList<@NotBlank @Size(min = 8) String> telephones;
	
	public Student() {
		super();
	}
	
	public Student(@Size(min = 4) long alumniCode, @NotBlank @Size(min = 4) String name,
			@NotBlank @Size(min = 4) String surname) {
		super();
		this.alumniCode = alumniCode;
		this.name = name;
		this.surname = surname;
	}
	
	public long getAlumniCode() {
		return alumniCode;
	}
	public void setAlumniCode(long alumniCode2) {
		this.alumniCode = alumniCode2;
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
	
}
