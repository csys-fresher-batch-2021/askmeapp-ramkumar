package in.ramkumar.service;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import in.ramkumar.exception.ServiceException;
import in.ramkumar.model.User;

public class TestRegisterUserService {

	/**
	 * Testing with valid inputs.
	 */
	@Test
	public void testRegisterUserWithValidInputs() {
		User user = new User();
		user.setName("Ramkumar G");
		user.setEmail("ramkumar.g.41@gmail.com");
		user.setPassword("Pass@123");
		UserService userService = new UserService();
		try {
			boolean validUser = userService.registerUser(user);
			assertTrue(validUser);
		} catch (ServiceException e) {
			fail();
			e.printStackTrace();
		}
	}

	/**
	 * Testing with already existing user(same email).
	 */
	@Test
	public void testRegisterUserWithAlreadyExistingUser() {
		User user1 = new User();
		user1.setName("Ramkumar G");
		user1.setEmail("ramkumar.g.42@gmail.com");
		user1.setPassword("Pass@123");
		UserService userService = new UserService();
		boolean validUser1 = false;
		try {
			validUser1 = userService.registerUser(user1);
			assertTrue(validUser1);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		/*
		 * User1 has to be added to userList, because no user exists in the userList. So
		 * we are expecting true as output.
		 */

		User user2 = new User();
		user2.setName("Ram G");
		user2.setEmail("ramkumar.g.43@gmail.com");
		user2.setPassword("Pass@123456");
		try {
			userService.registerUser(user2);
		} catch (ServiceException e) {
			assertEquals("Unable to register user", e.getMessage());
		}

	}

	/**
	 * Testing with empty name and valid email and password.
	 */
	@Test
	public void testRegisterUserWithEmptyName() {
		User user = new User();
		user.setName("");
		user.setEmail("ramkumar.g.44@psr.edu.in");
		user.setPassword("Pass@123");
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
		} catch (ServiceException e) {
			assertEquals("Unable to register user", e.getMessage());
		} 
	}

	/**
	 * Testing with null name and valid email and password.
	 */
//	@Test
	public void testRegisterUserWithNullName() {
		User user = new User();
		user.setName(null);
		user.setEmail("ramkumar.g.45@gmail.com");
		user.setPassword("Pass@123");
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
		} catch (ServiceException e) {
			assertEquals("Unable to register user", e.getMessage());
		}
	}

	/**
	 * Testing with invalid email(without @) and valid name and password.
	 */
	@Test
	public void testRegisterUserWithoutAtInEmail() {
		User user = new User();
		user.setName("Ramkumar G");
		user.setEmail("ramkumar.g.45gmail.com");
		user.setPassword("Pass@123");
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
		} catch (ServiceException e) {
			assertEquals("Unable to register user", e.getMessage());
		}

	}

	/**
	 * Testing with invalid email(without .) and valid name and password.
	 */
	@Test
	public void testRegisterUserWithoutDotInEmailDomain() {
		User user = new User();
		user.setName("Ramkumar G");
		user.setEmail("ramkumar0420@gmailcom");
		user.setPassword("Pass@123");
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
		} catch (ServiceException e) {
			assertEquals("Unable to register user", e.getMessage());
		} 

	}

	/**
	 * Testing with invalid email(with special characters) and valid name and
	 * password.
	 */
	@Test
	public void testRegisterUserWithInvalidSpecialCharacterInEmail() {
		User user = new User();
		user.setName("Ramkumar G");
		user.setEmail("ram kumar#0420@gmail.com");
		user.setPassword("Pass@123");
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
		} catch (ServiceException e) {
			assertEquals("Unable to register user", e.getMessage());
		}  
	}

	/**
	 * Testing with null email and valid name and password.
	 */
	@Test
	public void testRegisterUserWithNullEmail() {
		User user = new User();
		user.setName("Ram");
		user.setEmail(null);
		user.setPassword("Pass@123");
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
		} catch (ServiceException e) {
			assertEquals("Unable to register user", e.getMessage());
		} 

	}

	/**
	 * Testing with empty email and valid name and password.
	 */
	@Test
	public void testRegisterUserWithEmptyEmail() {
		User user = new User();
		user.setName("Ram");
		user.setEmail("");
		user.setPassword("Pass@123");
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
		} catch (ServiceException e) {
			assertEquals("Unable to register user", e.getMessage());
		}

	}

	/**
	 * Testing with invalid password(without special characters) and valid name and
	 * email.
	 */
	@Test
	public void testRegiterUserWithoutSpecialCharacterInPassword() {
		User user = new User();
		user.setName("Ramkumar G");
		user.setEmail("ramkumar.g.0420@gmail.com");
		user.setPassword("Pass1234");
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
		} catch (ServiceException e) {
			assertEquals("Unable to register user", e.getMessage());
		} 

	}

	/**
	 * Testing with invalid password(without numbers) and valid name and email.
	 */
	@Test
	public void testRegiterUserWithoutNumberInPassword() {
		User user = new User();
		user.setName("Ramkumar G");
		user.setEmail("ramkumar.g.0420@gmail.com");
		user.setPassword("Pass#one");
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
		} catch (ServiceException e) {
			assertEquals("Unable to register user", e.getMessage());
		}

	}

	/**
	 * Testing with invalid password(without upper case letters) and valid name and
	 * email.
	 */
	@Test
	public void testRegiterUserWithoutUpperCaseInPassword() {
		User user = new User();
		user.setName("Ramkumar G");
		user.setEmail("ramkumar.g.0420@gmail.com");
		user.setPassword("pass&123");
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
		} catch (ServiceException e) {
			assertEquals("Unable to register user", e.getMessage());
		}

	}

	/**
	 * Testing with invalid password(with <8 characters) and valid name and email.
	 */
	@Test
	public void testRegiterUserWithLessthan8CharactersInPassword() {
		User user = new User();
		user.setName("Ramkumar G");
		user.setEmail("ramkumar.g.0420@gmail.com");
		user.setPassword("pass&12");
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
		} catch (ServiceException e) {
			assertEquals("Unable to register user", e.getMessage());
		}

	}

	/**
	 * Testing with null password and valid name and email.
	 */
	@Test
	public void testRegisterUserWithNullPassword() {
		User user = new User();
		user.setName("Ram");
		user.setEmail("ramkumar.g.0420@gmail.com");
		user.setPassword(null);
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
		} catch (ServiceException e) {
			assertEquals("Unable to register user", e.getMessage());
		}
	}

	/**
	 * Testing with empty password and valid email and name.
	 */
	@Test
	public void testRegisterUserWithEmptyPassword() {
		User user = new User();
		user.setName("Ram");
		user.setEmail("ramkumar.g.0420@psr.edu.in");
		user.setPassword("");
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
		} catch (ServiceException e) {
			assertEquals("Unable to register user", e.getMessage());
		}
	}

}
