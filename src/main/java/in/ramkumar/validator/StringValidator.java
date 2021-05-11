package in.ramkumar.validator;

public class StringValidator {
	
	private StringValidator() {
		//Default constructor
	}
	
	/**
	 * This method checks the given string with null and empty.
	 * 
	 * @param string
	 * @return Returns true iff the given string is not null and empty.
	 */
	public static boolean checkingForNullAndEmpty(String string) {
		boolean validString = false;
		if (string != null && !string.trim().equals("")) {
			validString = true;
		}
		return validString;
	}

}
