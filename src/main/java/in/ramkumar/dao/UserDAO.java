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
				Integer userId = resultSet.getInt("user_id");
				String name = resultSet.getString("user_name");
				String email = resultSet.getString("user_email");
				String password = resultSet.getString("user_password");
				User user = new User(userId, name, email, password);
				userList.add(user);
			}
		} catch (SQLException | DBException e) {
			throw new DBException("Can't get users from database");
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return userList;

	}

	/**
	 * @param email
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
				Integer userId = resultSet.getInt("user_id");
				String name = resultSet.getString("user_name");
				String email = resultSet.getString("user_email");
				String password = resultSet.getString("user_password");
				user = new User(userId, name, email, password);
			}
		} catch (SQLException | DBException e) {
			throw new DBException("Can't get user from database");
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return user;
	}
}
