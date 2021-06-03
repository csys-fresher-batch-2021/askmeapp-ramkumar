package in.ramkumar.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import in.ramkumar.exception.DBException;
import in.ramkumar.model.User;

public class TestUserDAO {

	/**
	 * Testing with add user with valid input.
	 */
	@Test
	public void testAddUerDAO() {
		try {
			User user = new User("Ram", "ramkumar1@gmail.com", "Ram@1234");
			UserDAO userDAO = new UserDAO();
			userDAO.addUser(user);
		} catch (DBException e) {
			fail();
		}
	}

}
