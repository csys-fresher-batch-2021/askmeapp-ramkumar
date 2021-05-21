package in.ramkumar.util;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import in.ramkumar.exception.DBException;

public class TestConnectionUtil {

	/**
	 * Testing with valid connection to get connection.
	 */
	@Test
	public void testGetConnection() {
		try {
			 Connection connection = ConnectionUtil.getConnection();
			 connection.close();
		} catch (DBException | SQLException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Testing with valid connection to close connection.
	 */
	@Test
	public void testCloseConnection() {
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM Users");
			ConnectionUtil.close(prepareStatement, connection);
		} catch (DBException | SQLException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Testing with null connection
	 */
	@Test
	public void testCloseConnectionWithNullValue() {
		try {
			ConnectionUtil.close(null, null);
		} catch (DBException e) {
			assertEquals("Invalid connection", e.getMessage());
		}
	}

}
