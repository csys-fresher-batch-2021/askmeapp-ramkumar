package in.ramkumar.validator;

public class StringValidator {
	
	private StringValidator() {
		//Default constructor
	}
	
	/**
	 * This method checks the given string with null and empty.
	 * 
	 * @param string
	 */
	public static void checkingForNullAndEmpty(String string) {
		if (string == null) {
			throw new IllegalArgumentException("Null value not accepted");
		}else if(string.trim().equals("")) {
			throw new IllegalArgumentException("Empty value not accepted");
		}
	}

}
