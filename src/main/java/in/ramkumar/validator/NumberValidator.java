package in.ramkumar.validator;

import in.ramkumar.exception.ValidationException;

public class NumberValidator {
	
	private NumberValidator() {}

	/**
	 * This method checks the given number with null and empty.
	 * 
	 * @param number
	 * @return Returns true if the given number is not null and > 0.
	 */
	public static void checkingForNullAndEmpty(Integer number) {
		if (number == null) {
			throw new ValidationException("Null value not accepted");
		}
	}
}
