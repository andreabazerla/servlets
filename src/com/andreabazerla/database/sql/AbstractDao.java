package com.andreabazerla.database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractDao<T> {
	
	public static Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnectionFactory().getConnection();
	}
	
	protected final void internalExecuteUpdate(String sql, Object[] values, int[] sqlTypes)
		throws SQLException {
		
		if (values.length != sqlTypes.length)
			throw new SQLException("Wrong value number");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = getConnection();

			preparedStatement = connection.prepareStatement(sql);
			
			for(int index = 1; index <= values.length; index++)
			{
				preparedStatement.setObject(index, values[index - 1], sqlTypes[index - 1]);
			}
			
			preparedStatement.executeUpdate();
		} finally {
			if (preparedStatement != null)
				try {
					preparedStatement.close();
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
	}
	
	protected final List<T> internalExecuteQuery(String sql, Object[] values, int[] sqlTypes) throws SQLException {
		if (values.length != sqlTypes.length)
			throw new SQLException("Wrong value number");
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = getConnection();

			preparedStatement = connection.prepareStatement(sql);
			
			for(int index = 1; index <= values.length; index++)
			{
				preparedStatement.setObject(index, values[index - 1], sqlTypes[index - 1]);
			}
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<T> tList = new ArrayList<T>();
			
			while(resultSet.next()) {
				T t = null;
				t = create(resultSet);
				tList.add(t);
			}
						
			return tList;
			
		} finally {
			if (preparedStatement != null)
				try {
					preparedStatement.close();
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

	}

//	protected abstract List<T> create(ResultSet resultSet) throws SQLException;
	protected abstract T create(ResultSet resultSet) throws SQLException;
}
