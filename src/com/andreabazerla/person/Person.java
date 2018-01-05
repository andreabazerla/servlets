package com.andreabazerla.person;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.andreabazerla.CSV;

public class Person extends CSV implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String cf;
	private String name;
	private String surname;

	public static Person create(ResultSet resultSet) throws SQLException {

		int id = resultSet.getInt("id");
		String cf = resultSet.getString("name");
		String name = resultSet.getString("name");
		String surname = resultSet.getString("surname");

		return new Person(id, cf, name, surname);
	}

	public Person(int id, String cf, String name, String surname) {
		this.id = id;
		this.cf = cf;
		this.name = name;
		this.surname = surname;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
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

	@Override
	public String toString() {
		return "Person [id=" + id + ", cf=" + cf + ", name=" + name
				+ ", surname=" + surname + "]";
	}

	@Override
	public String getRow() {
		String row = getCf() + "," + getName() + "," + getSurname();
		return row;
	}
	
}
