package in.ramkumar.service;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import in.ramkumar.exception.ServiceException;
import in.ramkumar.model.User;

class TestRegisterUserService {

	/**
	 * Testing with valid inputs.
	 */
	@Order(1)
	@ParameterizedTest
	@CsvSource({
		"'Ram', ramkumar.g.41@gmail.com, Pass@123"
	})
	void testRegisterUserWithValidInputs(String name, String email, String pwd) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(pwd);
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
	 * Testing with invalid inputs.
	 */
	@Order(2)
	@ParameterizedTest
	@CsvSource({
		"'Ram', ramkumar.g.41@gmail.com, Pass@123",
		"'', ramkumar.g.44@psr.edu.in, Pass@123",
		", ramkumar.g.45@psr.edu.in, Pass@123",
		"Ramakumar Ramkumar Ramkumar, ramkumar.g.46@gmail.com, Pass@123",
		"Ramkumar, ramkumar0420@gmailcom, Pass@123",
		"Ramkumar, ram kumar#0420@gmail.com, Pass@123",
		"Ramkumar, , Pass@123",
		"Ramkumar, '', Pass@123",
		"Ramkumar, ramkumar.g.046@gmail.com, Pass123",
		"Ramkumar, ramkumar.g.047@gmail.com, Pass#one",
		"Ramkumar, ramkumar.g.048@gmail.com, pass&123",
		"Ramkumar, ramkumar.g.049@gmail.com, Pass&12",
		"Ramkumar, ramkumar.g.050@gmail.com, ",
		"Ramkumar, ramkumar.g.051@gmail.com, ''",
	})
	void testRegisterUserWithInvalidInputs(String name, String email, String pwd) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(pwd);
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
		} catch (ServiceException e) {
			assertEquals("Unable to register user", e.getMessage());
		}
	}
}
