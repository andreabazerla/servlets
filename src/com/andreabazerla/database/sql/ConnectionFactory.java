package com.andreabazerla.database.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=Corso";
	String DB_USERNAME = "sa";
	String DB_PASSWORD = "";

	private static ConnectionFactory connectionFactory = null;

	private ConnectionFactory() {
		try {
			Class.forName(JDBC_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection connection = null;
		connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		return connection;
	}

	public static ConnectionFactory getConnectionFactory() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
}
