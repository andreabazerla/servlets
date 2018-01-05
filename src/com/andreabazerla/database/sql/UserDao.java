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
import com.andreabazerla.user.User;

public class UserDao extends AbstractDao implements IGenericDao<User> {
	
	public static Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnectionFactory().getConnection();
	}

	public void save(User user) throws SQLException {
		String sql = "INSERT INTO users (name, surname, username, password, email, birthday, sex, region, role)" +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		Object[] values = {
			user.getName(),
			user.getSurname(),
			user.getUsername(),
			user.getPassword(),
			user.getEmail(),
			user.getBirthday(),
			user.getSex(),
			user.getRegion(),
			user.getRole()
		};
		
		int[] types = {
			Types.VARCHAR,
			Types.VARCHAR,
			Types.VARCHAR,
			Types.VARCHAR,
			Types.VARCHAR,
			Types.VARCHAR,
			Types.VARCHAR,
			Types.VARCHAR,
			Types.VARCHAR
		};
		
		internalExecuteUpdate(sql, values, types);
	}

	public List<User> load(int id) throws SQLException {
		
		String sql = "SELECT * FROM users WHERE id = ?";

		Object[] values = {
			id
		};
		
		int[] types = {
			Types.INTEGER,
		};
		
		return internalExecuteQuery(sql, values, types);
	}
	
	public void delete(int key) throws Exception {
		String sql = "DELETE FROM users WHERE id = ?";
		
		Object[] values = {
			key
		};
		
		int[] types = {
			Types.INTEGER
		};
		
		internalExecuteUpdate(sql, values, types);
	}
	
//	public List<User> getAll(String search) throws SQLException {
//		
//		String sql;
//		Object[] values = null;
//		int[] types = null;
//		
//		if (search == null || search.isEmpty()) {
//			sql = "SELECT * FROM users WHERE cf <> ?";
//			values[0] = "0"; 
//			types[0] = Types.VARCHAR; 
//		} else {
//			sql = "SELECT * FROM users WHERE name LIKE ?" +
//				" OR surname LIKE ?" +
//				" OR username LIKE ?" +
//				" OR email LIKE ?" +
//				" OR birthday LIKE ?" +
//				" OR sex LIKE ?" +
//				" OR region LIKE ?";
//			
//			for (int i = 0; i < 7; i++) {
//				values[i] = search; 
//			}
//			
//			for (int i = 0; i < 7; i++) {
//				types[i] = Types.VARCHAR; 
//			}
//		}
//		
//		return internalExecuteQuery(sql, values, types);
//	}

	public ArrayList<User> getAll(String search) throws SQLException {
		List<User> userList = new ArrayList<User>();
		
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			
			if (search == null || search.isEmpty()) {
				statement = connection.createStatement();
				String sql = "SELECT * FROM users";
				resultSet = statement.executeQuery(sql);
			} else {				
				String sql = "SELECT * FROM users WHERE name LIKE ?" +
						" OR surname LIKE ?" +
						" OR username LIKE ?" +
						" OR email LIKE ?" +
						" OR birthday LIKE ?" +
						" OR sex LIKE ?" +
						" OR region LIKE ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, "%" + search + "%");
				preparedStatement.setString(2, "%" + search + "%");
				preparedStatement.setString(3, "%" + search + "%");
				preparedStatement.setString(4, "%" + search + "%");
				preparedStatement.setString(5, "%" + search + "%");
				preparedStatement.setString(6, "%" + search + "%");
				preparedStatement.setString(7, "%" + search + "%");
				resultSet = preparedStatement.executeQuery();
			}
			
			while (resultSet.next()) {
				int id = Integer.parseInt(resultSet.getString("id"));
				String name = resultSet.getString("name");
				String surname = resultSet.getString("surname");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				String email = resultSet.getString("email");
				String birthday = resultSet.getString("birthday");
				String sex = resultSet.getString("sex");
				String region = resultSet.getString("region");
				String role = resultSet.getString("role");
				
				User user = new User(id, name, surname, username, password, email, birthday, sex, region, role);
				
				userList.add(user);
			}

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
				
		return (ArrayList<User>) userList;		
	}
	
	@Override
	protected User create(ResultSet resultSet) throws SQLException {
		return User.create(resultSet);
	}

	@Override
	public List<User> log(String username) throws SQLException {
		
		String sql = "SELECT * FROM users WHERE username = ?";

		Object[] values = {
			username
		};
		
		int[] types = {
			Types.VARCHAR,
		};
		
		return internalExecuteQuery(sql, values, types);

	}

}
