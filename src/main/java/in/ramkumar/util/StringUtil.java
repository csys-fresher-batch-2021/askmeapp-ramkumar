package in.ramkumar.util;

import in.ramkumar.exception.UtilException;
import in.ramkumar.exception.ValidationException;
import in.ramkumar.validator.StringValidator;

public class StringUtil {
	
	private StringUtil() {
		//Default constructor
	}

	/**
	 * @param string
	 * @return Returns the length of the given string.
	 */
	public static int getLength(String string) {
		int length = 0;
		try {
			StringValidator.checkingForNullAndEmpty(string);
			length = string.trim().length();
		} catch (ValidationException e) {
			throw new UtilException("Unable to get length");
		}
		return length;
	}

}
