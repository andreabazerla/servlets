package com.andreabazerla.database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.andreabazerla.database.IGenericDao;
import com.andreabazerla.person.Person;

public class PersonDao extends AbstractDao implements IGenericDao<Person> {
	
	public static Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnectionFactory().getConnection();
	}
	
	public void save(Person person) throws SQLException {
		String sql = "INSERT INTO person (cf, name, surname)" +
					"VALUES (?, ?, ?)";
		
		Object[] values = {
			person.getCf(),
			person.getName(),
			person.getSurname()
		};
		
		int[] types = {
			Types.VARCHAR,
			Types.VARCHAR,
			Types.VARCHAR,
		};
		
		internalExecuteUpdate(sql, values, types);
	}

	public List<Person> load(int id) throws SQLException {
		String sql = "SELECT * FROM person WHERE id = ?";

		Object[] values = {
			id
		};
		
		int[] types = {
			Types.INTEGER,
		};
		
		return internalExecuteQuery(sql, values, types);
	}
	
	@Override
	public List<Person> log(String cf) throws SQLException {
		
		String sql = "SELECT * FROM person WHERE cf = ?";

		Object[] values = {
			cf
		};
		
		int[] types = {
			Types.VARCHAR,
		};
		
		return internalExecuteQuery(sql, values, types);
	}

	@Override
	public void delete(int key) throws Exception 
	{
		internalExecuteUpdate("DELETE FROM person WHERE id = ?", new Object[]{key}, new int[]{Types.INTEGER});
	}
	
	public ArrayList<Person> getAll(String search) {
		ArrayList<Person> personList = new ArrayList<Person>();
		
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			
			if (search == null || search.isEmpty()) {
				statement = connection.createStatement();
				String sql = "SELECT * FROM person";
				resultSet = statement.executeQuery(sql);
			} else {				
				String sql = "SELECT * FROM person WHERE cf LIKE ?" +
						" OR name LIKE ?" +
						" OR surname LIKE ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, "%" + search + "%");
				preparedStatement.setString(2, "%" + search + "%");
				preparedStatement.setString(3, "%" + search + "%");
				resultSet = preparedStatement.executeQuery();
			}
			
			while (resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString("id"));
				String cf = resultSet.getString("cf");
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				
				Person person = new Person(id, cf, name, surname);
				
				personList.add(person);
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
				
		return personList;		
	}

	@Override
	protected Person create(ResultSet resultSet) throws SQLException {
		return Person.create(resultSet);
	}

}
