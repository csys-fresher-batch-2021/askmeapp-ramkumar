package in.ramkumar.service;

import java.util.List;

import in.ramkumar.dao.UserDAO;
import in.ramkumar.exception.DBException;
import in.ramkumar.exception.ServiceException;
import in.ramkumar.exception.ValidationException;
import in.ramkumar.model.User;
import in.ramkumar.validator.StringValidator;

import static in.ramkumar.validator.StringValidator.checkingForNullAndEmpty;
import static in.ramkumar.validator.UserValidator.*;

public class UserService {

	private static final String UNABLE_TO_REGISTER_USER = "Unable to register user";
	private UserDAO userDAO = new UserDAO();

	/**
	 * User Registration. This validates the user name, password, email given by the
	 * user. If it is a valid user then it will be added to the userList.
	 * 
	 * @param userObject
	 */
	public boolean registerUser(User userObject) {
		String name = userObject.getName();
		String email = userObject.getEmail();
		String password = userObject.getPassword();

		User user = null;
		try {
			user = getUser(email); // Checking user already exists in the users database.
			if (user != null) {
				throw new ServiceException("Sorry Email already exists");
			}
		} catch (ServiceException e) {
			throw new ServiceException(e.getMessage());
		}

		/*
		 * If the user does not exist in the database, then only user information will
		 * be validated and added to the users database.
		 */
		try {
			validateName(name);
			validateEmail(email);
			validatePassword(password);
			userDAO.addUser(userObject);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		} catch (DBException e) {
			throw new ServiceException(UNABLE_TO_REGISTER_USER);
		}
		return user == null;
	}

	/**
	 * @return Returns the list of users as ArrayList.
	 */
	public List<User> getAllUsers() {
		List<User> allUsers = null;
		try {
			allUsers = userDAO.getAllUsers();
		} catch (DBException e) {
			throw new ServiceException("Unable to all users");
		}
		return allUsers;
	}

	/**
	 * @return Returns the number of users in the userList.
	 * @throws ServiceException
	 */
	public int getNumberOfUsers() {
		int size = 0;
		try {
			size = getAllUsers().size();
		} catch (ServiceException e) {
			throw new ServiceException("Unable to get number of users");
		}
		return size;
	}

	/**
	 * @param email
	 * @return Returns the User object for the given email.
	 */
	public User getUser(String email) {
		User user = null;
		try {
			StringValidator.checkingForNullAndEmpty(email);
			user = userDAO.getUserByEmail(email);
		} catch (DBException e) {
			throw new ServiceException("Unable to get user");
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
		return user;
	}

	/**
	 * User Login. This method checks the user exist in the userList. If the user
	 * exist, user object index will be return by getUserIndexWithEmail(email). With
	 * user index we can get the user object reference. And then email and password
	 * is verified.
	 * 
	 * @param email
	 * @return Returns true if the user email and password given by the user is
	 *         verified with user object and it should match with user
	 */
	public boolean login(String email, String password) {

		boolean validUser = false;
		User user = null;
		try {
			checkingForNullAndEmpty(email);
			checkingForNullAndEmpty(password);
			user = getUser(email); // Checking user already exists in the users database.
		} catch (ValidationException | ServiceException e) {
			throw new ServiceException(e.getMessage());
		}

		if (user == null) {
			throw new ServiceException("No user with email " + email);
		}
		/*
		 * If the user email, password is not null and empty, then get the user index
		 * from the userList.
		 */
		if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
			validUser = true;
		} else {
			throw new ServiceException("Incorrect email or password");
		}
		return validUser;
	}

	/**
	 * Admin Login. This methods checks the email is 'admin@gmail.com' and password
	 * is 'admin'
	 * 
	 * @param email
	 * @param password
	 * @return Returns true if the user email and password given by the admin is
	 *         valid.
	 */
	public boolean adminLogin(String email, String pass) {
		checkingForNullAndEmpty(email);
		checkingForNullAndEmpty(pass);

		boolean validAdmin = false;

		if (email.equals("admin@gmail.com") && pass.equals("admin")) {
			validAdmin = true;
		} else {
			throw new ServiceException("Invalid admin");
		}
		return validAdmin;
	}

}
