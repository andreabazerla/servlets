package com.andreabazerla.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.andreabazerla.CSV;
import com.andreabazerla.person.Person;
import com.andreabazerla.user.User;

public interface IGenericDao<T extends CSV> {

	public abstract List<T> load(final int id) throws Exception;

	public abstract void save(T t) throws SQLException;

	public abstract void delete(int id) throws Exception;
	
	public abstract List<T> getAll(String search) throws SQLException;

	public abstract List<T> log(String username) throws SQLException;
	
}