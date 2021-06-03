package in.ramkumar.service;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import in.ramkumar.exception.ServiceException;

class TestLoginUserService {

	/**
	 * Testing with valid inputs.
	 */
	@Test
	void testLoginUserWithValidInputs() {
		UserService userService = new UserService();
		try {
			boolean validUser = userService.login("dhaya@mail.com", "Pass@1234");
			assertTrue(validUser);
		} catch (ServiceException e1) {
			fail();
		}
	}

	/**
	 * Testing with invalid inputs.
	 */
	@ParameterizedTest
	@CsvSource({
		", Ramkumar, Null value not accepted",
		"'', Ramkumar, Empty value not accepted",
		"ramkumar@gmail.com, Ramkumar, No user with email ramkumar@gmail.com",
		"dhaya@mail.com, Ramkumar, Incorrect email or password"
		})
	void testLoginUserWithInvalidInputs(String email, String password, String message) {
		UserService userService = new UserService();
		try {
			userService.login(email, password);
		} catch (ServiceException e) {
			assertEquals(message, e.getMessage());
		}
	}
}
