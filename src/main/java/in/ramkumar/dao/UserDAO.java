package in.ramkumar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.ramkumar.exception.DBException;
import in.ramkumar.model.User;
import in.ramkumar.util.ConnectionUtil;

public class UserDAO {

	private static final String CAN_T_GET_USER_FROM_DATABASE = "Can't get user from database";
	private static final String USER_PASSWORD = "user_password";
	private static final String USER_EMAIL = "user_email";
	private static final String USER_NAME = "user_name";
	private static final String USER_ID = "user_id";

	/**
	 * This method adds user Users table.
	 * 
	 * @param user
	 */
	public void addUser(User user) {
		String insertSQLQuery = "INSERT INTO Users (user_name, user_email, user_password) VALUES (?, ?, ?)";

		Connection connection = null;
		PreparedStatement prepareStatement = null;

		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(insertSQLQuery);
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getEmail());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.executeUpdate();
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			throw new DBException("Can't add user to the database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}
	}

	/**
	 * This method gets all user from the database.
	 * 
	 * @return Returns the list of users
	 */
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		String selectSQLQuery = "SELECT * FROM Users";

		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(selectSQLQuery);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Integer userId = resultSet.getInt(USER_ID);
				String name = resultSet.getString(USER_NAME);
				String email = resultSet.getString(USER_EMAIL);
				String password = resultSet.getString(USER_PASSWORD);
				User user = new User(userId, name, email, password);
				userList.add(user);
			}
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			throw new DBException("Can't get users from database");
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return userList;

	}

	/**
	 * @param emailString
	 * @return Returns the User object for the given email.
	 */
	public User getUserByEmail(String emailString) {
		String selectSQLQuery = "SELECT * FROM Users WHERE user_email = ?";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		User user = null;

		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(selectSQLQuery);
			prepareStatement.setString(1, emailString);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				Integer userId = resultSet.getInt(USER_ID);
				String name = resultSet.getString(USER_NAME);
				String email = resultSet.getString(USER_EMAIL);
				String password = resultSet.getString(USER_PASSWORD);
				user = new User(userId, name, email, password);
			}
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			throw new DBException(CAN_T_GET_USER_FROM_DATABASE);
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return user;
	}

	/**
	 * @param userId
	 * @return Returns the User object for the given userId.
	 */
	public User getUserById(Integer userId) {
		String selectSQLQuery = "SELECT * FROM Users WHERE user_id = ?";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		User user = null;

		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(selectSQLQuery);
			prepareStatement.setInt(1, userId);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				String name = resultSet.getString(USER_NAME);
				String email = resultSet.getString(USER_EMAIL);
				String password = resultSet.getString(USER_PASSWORD);
				user = new User(userId, name, email, password);
			}
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			throw new DBException(CAN_T_GET_USER_FROM_DATABASE);
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return user;
	}

	/**
	 * This method update the given user name for the given userId
	 * 
	 * @param userId
	 * @param userName
	 */
	public void updateUserName(Integer userId, String userName) {
		String updateSQLQuery = "UPDATE Users SET user_name = ? WHERE user_id = ?";
		Connection connection = null;
		PreparedStatement prepareStatement = null;

		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(updateSQLQuery);
			prepareStatement.setString(1, userName);
			prepareStatement.setInt(2, userId);
			prepareStatement.executeUpdate();
		} catch (SQLException | DBException e) {
			throw new DBException("Can't update username in database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}
	}

	/**
	 * This method update the user password for the given userId
	 * 
	 * @param userId
	 * @param userPassword
	 */
	public void updateUserPassword(Integer userId, String userPassword) {
		String updateSQLQuery = "UPDATE Users SET user_password = ? WHERE user_id = ?";
		Connection connection = null;
		PreparedStatement prepareStatement = null;

		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(updateSQLQuery);
			prepareStatement.setString(1, userPassword);
			prepareStatement.setInt(2, userId);
			prepareStatement.executeUpdate();
		} catch (SQLException | DBException e) {
			throw new DBException("Can't update user password in database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}
	}

	/**
	 * This method updates the user email for the given useId.
	 * 
	 * @param userId
	 * @param userEmail
	 */
	public void updateUserEmail(Integer userId, String userEmail) {
		String updateSQLQuery = "UPDATE Users SET user_email = ? WHERE user_id = ?";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(updateSQLQuery);
			prepareStatement.setString(1, userEmail);
			prepareStatement.setInt(2, userId);
			prepareStatement.executeUpdate();
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			throw new DBException("Can't update user email in database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}
	}

}
