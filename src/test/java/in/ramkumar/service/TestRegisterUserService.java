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
		}
	}
	
	/**
	 * Testing with invalid inputs.
	 */
	@Order(2)
	@ParameterizedTest
	@CsvSource({
		"'Ram', ramkumar.g.41@gmail.com, Pass@123, Sorry Email already exists",
		"'', ramkumar.g.41@psr.edu.in, Pass@123, Empty value not accepted",
		", ramkumar.g.41@psr.edu.in, Pass@123, Null value not accepted",
		"Ramakumar Ramkumar Ramkumar, ramkumar.g.42@gmail.com, Pass@123, Name length can't be 20",
		"Ramkumar, ramkumar0420@gmailcom, Pass@123, Invalid email",
		"Ramkumar, ram kumar#0420@gmail.com, Pass@123, Invalid email",
		"Ramkumar, , Pass@123, Null value not accepted" ,
		"Ramkumar, '', Pass@123, Empty value not accepted",
		"Ramkumar, ramkumar.g.42@gmail.com, Pass123, Invalid password. Use 8 or more characters with a mix of letters(upper and lower and symbols) and numbers",
		"Ramkumar, ramkumar.g.43@gmail.com, Pass#one, Invalid password. Use 8 or more characters with a mix of letters(upper and lower and symbols) and numbers",
		"Ramkumar, ramkumar.g.44@gmail.com, pass&123, Invalid password. Use 8 or more characters with a mix of letters(upper and lower and symbols) and numbers",
		"Ramkumar, ramkumar.g.45@gmail.com, Pass&12, Invalid password. Use 8 or more characters with a mix of letters(upper and lower and symbols) and numbers",
		"Ramkumar, ramkumar.g.46@gmail.com, , Null value not accepted",
		"Ramkumar, ramkumar.g.47@gmail.com, '', Empty value not accepted",
	})
	void testRegisterUserWithInvalidInputs(String name, String email, String pwd, String message) {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(pwd);
		UserService userService = new UserService();
		try {
			userService.registerUser(user);
		} catch (ServiceException e) {
			assertEquals(message, e.getMessage());
		}
	}
}
