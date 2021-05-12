package in.ramkumar.util;

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
		StringValidator.checkingForNullAndEmpty(string);
		return string.trim().length();
	}

}
