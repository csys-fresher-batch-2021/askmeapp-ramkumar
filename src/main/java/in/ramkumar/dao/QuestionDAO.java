package in.ramkumar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import in.ramkumar.exception.DBException;
import in.ramkumar.model.Question;
import in.ramkumar.util.ConnectionUtil;

public class QuestionDAO {

	/**
	 * This method adds given question to the questions table.
	 * 
	 * @param question
	 */
	public void addQuestion(Question question) {

		String insertSQLQuery = "INSERT INTO Questions (questionName, description) VALUES (?, ?)";

		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(insertSQLQuery);
			prepareStatement.setString(1, question.getQuestionName());
			prepareStatement.setString(2, question.getDescription());
			prepareStatement.executeUpdate();
		} catch (DBException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Question can't be added to database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}
	}

	/**
	 * This method gets all questions from the database.
	 * 
	 * @return Returns the list of questions
	 */
	public List<Question> getAllQuestions() {
		List<Question> questionList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String selectSQLQuery = "SELECT * FROM Questions";

		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(selectSQLQuery);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				String questionName = resultSet.getString("questionName");
				String description = resultSet.getString("description");
				Question question = new Question(questionName, description);
				questionList.add(question);
			}
		} catch (DBException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Can't get questions from database");
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return questionList;

	}

	/**
	 * @param question
	 * @return Returns the Question object for the given email.
	 */
	public Question getQuestion(String questionString) {
		Connection connection = ConnectionUtil.getConnection();
		Question question = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String insertSQLQuery = "SELECT * FROM Questions WHERE questionName = ?";

		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(insertSQLQuery);
			prepareStatement.setString(1, questionString);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				String questionName = resultSet.getString("questionName");
				String description = resultSet.getString("description");
				question = new Question(questionName, description);
			}
		} catch (DBException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Can't get question from database");
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement,  connection);
		}
		return question;
	}

}
