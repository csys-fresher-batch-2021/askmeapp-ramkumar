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
	public static void checkingForNullAndEmpty(String string) {
		if (string == null) {
			throw new IllegalArgumentException("Null value not accepted");
		}else if(string.trim().equals("")) {
			throw new IllegalArgumentException("Empty value not accepted");
		}
	}

}
