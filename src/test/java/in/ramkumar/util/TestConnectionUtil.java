package in.ramkumar.util;

import static org.junit.Assert.*;

import java.sql.Connection;
import org.junit.jupiter.api.Test;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.ramkumar.exception.DBException;

class TestConnectionUtil {

	/**
	 * Testing with valid connection to get connection.
	 */
	@Test
	void testGetConnection() {
		try {
			 Connection connection = ConnectionUtil.getConnection();
			 connection.close();
		} catch (DBException | SQLException e) {
			fail();
		}
	}

	/**
	 * Testing with valid connection to close connection.
	 */
	@Test
	void testCloseConnection() {
		try {
			Connection connection = ConnectionUtil.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM Users");
			ConnectionUtil.close(prepareStatement, connection);
		} catch (DBException | SQLException e) {
			fail();
		}
	}

	/**
	 * Testing with null connection
	 */
	@Test
	void testCloseConnectionWithNullValue() {
		try {
			ConnectionUtil.close(null, null);
		} catch (DBException e) {
			assertEquals("Invalid connection", e.getMessage());
		}
	}

}
