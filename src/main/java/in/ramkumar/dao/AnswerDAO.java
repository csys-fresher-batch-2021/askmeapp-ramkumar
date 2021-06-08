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
	 * @param questionId
	 * @param userId
	 * @param answer
	 */
	public void addAnswer(Integer questionId, Integer userId, Answer answer) {

		String insertSQLQuery = "INSERT INTO Answers (answer_name, question_id, user_id, answer_words) VALUES (?, ?, ?, TO_TSVECTOR('"
				+ answer.getAnswerName() + "'))";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(insertSQLQuery);
			prepareStatement.setString(1, answer.getAnswerName());
			prepareStatement.setInt(2, questionId);
			prepareStatement.setInt(3, userId);
			prepareStatement.executeUpdate();
		} catch (DBException | SQLException e) {
			throw new DBException("Answer can't be added to database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}

	}

	/**
	 * This method gets all answer for the given question from the database.
	 * 
	 * @param questionId
	 * @return Returns the list of answers
	 */
	public List<Answer> getAllAnswers(int questionId) {
		List<Answer> answerList = new ArrayList<>();
		String selectSQLQuery = "SELECT answer_id, answer_name, user_id  FROM Answers WHERE question_id = ?";

		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(selectSQLQuery);
			prepareStatement.setInt(1, questionId);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Integer answerId = resultSet.getInt("answer_id");
				String answerName = resultSet.getString("answer_name");
				Integer userId = resultSet.getInt("user_id");
				Answer answer = new Answer(answerId, answerName, questionId, userId);
				answerList.add(answer);
			}
		} catch (DBException | SQLException e) {
			throw new DBException("Can't get answers from database");
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return answerList;
	}

}
