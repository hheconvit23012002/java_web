package com.example.spring.jdbc.book;

public class Student {
	
	private int id;

	private String name;

	private String dob;

	private String major;

	private boolean vaccinated;
	public Student() {
		// TODO Auto-generated constructor stub
	}
	public Student(int id, String name, String dob, String major, boolean vaccinated) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.major = major;
		this.vaccinated = vaccinated;
	}
	public String getDob() {
		return dob;
	}
	public int getId() {
		return id;
	}
	public String getMajor() {
		return major;
	}
	public String getName() {
		return name;
	}
	public boolean getVaccinated() {
		return vaccinated;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public void setVaccinated(boolean vaccinated) {
		this.vaccinated = vaccinated;
	}
}
