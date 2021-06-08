package in.ramkumar.validator;

import static in.ramkumar.util.StringUtil.getLength;
import static in.ramkumar.validator.StringValidator.checkingForNullAndEmpty;

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
			throw new ValidationException(e.getMessage());
		}
		if (nameLength < 0 || nameLength > numberOfCharactersForName) {
			throw new ValidationException("Name length can't be 20");
		}
	}

	/**
	 * Password Validation. This method validates the given password. The password
	 * should not be null, empty, and also password length >=8 characters.
	 * 
	 * @param password
	 */
	public static void validatePassword(String pwd) {
		String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&])(?=\\S+$).{8,}";
		try {
			checkingForNullAndEmpty(pwd);
		} catch (ValidationException e) {
			throw new ValidationException(e.getMessage());
		}
		boolean validPasswordPattern = Pattern.matches(pattern, pwd);
		if (!validPasswordPattern) {
			throw new ValidationException(
					"Invalid password. Use 8 or more characters with a mix of letters(upper and lower and symbols) and numbers");
		}
	}

	/**
	 * Email Validation. This method validates the given email. Condition: The email
	 * should not be null, empty, and also it should meet the email pattern.
	 * 
	 * @param email
	 */
	public static void validateEmail(String email) {
		String emailPattern = "(?:[\\w.]+)@(?:[\\w.]+\\.)[a-z]{2,3}$";
		try {
			checkingForNullAndEmpty(email);
		} catch (ValidationException e) {
			throw new ValidationException(e.getMessage());
		}
		boolean validEmailPattern = Pattern.matches(emailPattern, email);
		if (!validEmailPattern) {
			throw new ValidationException("Invalid email");
		}
	}

}
