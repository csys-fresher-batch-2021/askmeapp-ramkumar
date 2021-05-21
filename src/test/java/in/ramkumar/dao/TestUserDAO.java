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
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Testing update user email with valid input.
	 */
	@Test
	public void testUpdateEmailDAO() {
		try {
			User user = new User("Ram", "ram@gmail.com", "Ram@1234");
			UserDAO userDAO = new UserDAO();
			userDAO.addUser(user);
			userDAO.updateEmail("ram@gmail.com", "ram@mail.com");
		} catch (DBException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Testing update user password with valid input.
	 */
	@Test
	public void testUpdatePasswordDAO() {
		try {
			User user = new User("Ram", "ramkumar2@gmail.com", "Ram@1234");
			UserDAO userDAO = new UserDAO();
			userDAO.addUser(user);
			userDAO.updatePassword("ramkumar2@gmail.com", "Ram@1235");
		} catch (DBException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	/**
	 * Testing update user name with valid input.
	 */
	@Test
	public void testUpdateNameDAO() {
		try {
			User user = new User("Ram", "ramkumar3@gmail.com", "Ram@1234");
			UserDAO userDAO = new UserDAO();
			userDAO.addUser(user);
			userDAO.updateUserName("ramkumar3@gmail.com", "Ramkumar");
		} catch (DBException e) {
			e.printStackTrace();
			fail();
		}
	}

	/**
	 * Testing with delete user with valid input.
	 */
	@Test
	public void testDeleteUserDAO() {
		try {
			User user = new User("Ram", "ramkumar4@gmail.com", "Ram@1234");
			UserDAO userDAO = new UserDAO();
			userDAO.addUser(user);
			userDAO.deleteUser("ramkumar4@gmail.com");
		} catch (DBException e) {
			e.printStackTrace();
			fail();
		}
	}


}
