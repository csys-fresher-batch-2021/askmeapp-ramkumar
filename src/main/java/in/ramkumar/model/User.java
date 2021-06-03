package in.ramkumar.model;

public class User {

	private Integer userId;
	private String name;
	private String email;
	private String password;

	public User() {
	}

	public User(Integer userId, String name, String email, String password) {
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * 
	 * @return userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the userId
	 * 
	 * @param userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return Returns the name of the user.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name for the current user.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the email of the user.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the email for the current user.
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return Returns the password of the user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the password for the current user.
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the string representation of for the User object.
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}

}
