package in.ramkumar.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import in.ramkumar.exception.DBException;

public class ConnectionUtil {

	private ConnectionUtil() {
	}

	private static final String INVALID_CONNECTION = "Invalid connection";

	/**
	 * This method loads the Driver class and then gets the connection instance.
	 * 
	 * @return Connection instance
	 */
	public static Connection getConnection() {
		String driverClass = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost/askme_db";
		String userName = "postgres";
		String password = "2004@cse";
		try {
			Class.forName(driverClass);
			return DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException | SQLException e) {
			throw new DBException("Can't establish connection");
		}
	}

	/**
	 * This method closes the given connection.
	 * 
	 * @param statement
	 * @param connection
	 */
	public static void close(Statement statement, Connection connection) {
		if (statement != null && connection != null) {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				throw new DBException(INVALID_CONNECTION);
			}
		} else {
			throw new DBException(INVALID_CONNECTION);
		}
	}

	/**
	 * This method closes the given connections.
	 * 
	 * @param resultSet
	 * @param statement
	 * @param connection
	 */
	public static void close(ResultSet resultSet, Statement statement, Connection connection) {
		if (resultSet != null && statement != null && connection != null) {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				throw new DBException(INVALID_CONNECTION);
			}
		} else {
			throw new DBException(INVALID_CONNECTION);
		}
	}
}
