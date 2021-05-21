package in.ramkumar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.ramkumar.exception.DBException;
import in.ramkumar.model.Answer;
import in.ramkumar.util.ConnectionUtil;

public class AnswerDAO {

	/**
	 * This method adds given answer to the answers table.
	 * 
	 * @param questionName
	 * @param answerObject
	 */
	public void addAnswer(String questionName, Answer answerObject)  {

		String insertSQLQuery = "INSERT INTO Answers (questionName, answerName) VALUES (?, ?)";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(insertSQLQuery);
			prepareStatement.setString(1, questionName);
			prepareStatement.setString(2, answerObject.getAnswerName());
			prepareStatement.executeUpdate();
		} catch (DBException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Answer can't be added to database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}

	}

	/**
	 * This method gets all answer for the given question from the database.
	 * 
	 * @return Returns the list of answers
	 */
	public List<Answer> getAllAnswers(String questionName) {
		List<Answer> answerList = new ArrayList<>();
		String selectSQLQuery = "SELECT * FROM Answers where questionName = ?";

		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(selectSQLQuery);
			prepareStatement.setString(1, questionName);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				String answerName = resultSet.getString("answerName");
				Answer answer = new Answer();
				answer.setAnswerName(answerName);
				answerList.add(answer);
			}
		} catch (DBException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Can't get answers from database");
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return answerList;
	}

}
