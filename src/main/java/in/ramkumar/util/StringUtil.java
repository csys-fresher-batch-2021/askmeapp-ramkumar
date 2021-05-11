package in.ramkumar.util;

public class StringUtil {
	
	private StringUtil() {
		//Default constructor
	}

	/**
	 * @param string
	 * @return Returns the length of the given string.
	 */
	public static int getLength(String string) {
		return string.trim().length();
	}

}
