package in.ramkumar.validator;

import static in.ramkumar.util.StringUtil.*;
import static in.ramkumar.validator.StringValidator.*;

import java.util.regex.Pattern;

import in.ramkumar.exception.UtilException;
import in.ramkumar.exception.ValidationException;

public class UserValidator {

	private UserValidator() {
	}

	/**
	 * UserName Validation. This method validates the given name. The name should
	 * not be null, empty, and also name length <=20 characters.
	 * 
	 * @param name
	 */
	public static void validateName(String name) {
		int nameLength;
		int numberOfCharactersForName = 20;
		try {
			nameLength = getLength(name);
			checkingForNullAndEmpty(name);
		} catch (UtilException | ValidationException e) {
			throw new ValidationException("Invalid name");
		}
		if (nameLength < 0 || nameLength > numberOfCharactersForName) {
			throw new ValidationException("Invalid name");
		}
	}

	/**
	 * Password Validation. This method validates the given password. The password
	 * should not be null, empty, and also password length >=8 characters.
	 * 
	 * @param password
	 */
	public static void validatePassword(String password) {
		String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$";
		try {
			checkingForNullAndEmpty(password);
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ValidationException("Invalid password");
		}
		boolean validPasswordPattern = Pattern.matches(passwordPattern, password);
		if (!validPasswordPattern) {
			throw new ValidationException("Invalid password");
		}
	}

	/**
	 * Email Validation. This method validates the given email. Condition: The email
	 * should not be null, empty, and also it should meet the email pattern.
	 * 
	 * @param email
	 */
	public static void validateEmail(String email) {
		String emailPattern = "[[A-Za-z]?0-9.]+@(?:[a-zA-Z0-9]+\\.)+[a-z]{2,6}$";
		try {
			checkingForNullAndEmpty(email);
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ValidationException("Invalid email");
		}
		boolean validEmailPattern = Pattern.matches(emailPattern, email);
		if (!validEmailPattern) {
			throw new ValidationException("Invalid email");
		}
	}

}
