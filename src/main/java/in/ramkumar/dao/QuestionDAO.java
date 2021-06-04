package in.ramkumar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.ramkumar.exception.DBException;
import in.ramkumar.model.Question;
import in.ramkumar.util.ConnectionUtil;

public class QuestionDAO {

	private static final String ANSWERS_COUNT = "answers_count";
	private static final String USER_ID = "user_id";
	private static final String QUESTION_ID = "question_id";
	private static final String QUESTION_DESCRIPTION = "question_description";
	private static final String QUESTION_NAME = "question_name";
	private static final String CAN_T_GET_QUESTION_FROM_DATABASE = "Can't get question from database";

	/**
	 * This method adds given question to the questions table.
	 * 
	 * @param question
	 */
	public void addQuestion(Question question, Integer userId) {

		String insertSQLQuery = "INSERT INTO Questions (question_name, question_description, user_id, question_words) VALUES (?, ?, ?, to_tsvector('"
				+ question.getQuestionName() + "'))";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(insertSQLQuery);
			prepareStatement.setString(1, question.getQuestionName());
			prepareStatement.setString(2, question.getDescription());
			prepareStatement.setInt(3, userId);
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
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
				String questionName = resultSet.getString(QUESTION_NAME);
				String description = resultSet.getString(QUESTION_DESCRIPTION);
				Integer questionId = resultSet.getInt(QUESTION_ID);
				Question question = new Question(questionId, questionName, description);
				questionList.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Can't get questions from database");
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return questionList;

	}

	/**
	 * @param questionId
	 * @return Returns the Question object for the given questionId.
	 */
	public Question getQuestionById(Integer questionId) {
		Connection connection = null;
		Question question = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String insertSQLQuery = "SELECT * FROM Questions WHERE question_id = ?";
		Integer answersCounts = getAnswersCountByQuestionId(questionId);
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(insertSQLQuery);
			connection.prepareStatement(insertSQLQuery);
			prepareStatement.setInt(1, questionId);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				String questionName = resultSet.getString(QUESTION_NAME);
				String description = resultSet.getString(QUESTION_DESCRIPTION);
				Integer userId = resultSet.getInt(USER_ID);
				question = new Question(questionId, questionName, description, userId, answersCounts);
			}
		} catch (DBException | SQLException e) {
			e.printStackTrace();
			throw new DBException(CAN_T_GET_QUESTION_FROM_DATABASE);
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return question;
	}

	/**
	 * @param questionId
	 * @return Returns answers count for given question id
	 */
	public Integer getAnswersCountByQuestionId(Integer questionId) {
		Connection connection = null;
		Integer answersCount = 0;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String answersCountSQLQuery = "SELECT count(*) as answers_count from Answers where question_id = ?";
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(answersCountSQLQuery);
			prepareStatement.setInt(1, questionId);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				answersCount = resultSet.getInt(ANSWERS_COUNT);
			}
		} catch (DBException | SQLException e) {
			throw new DBException("Can't get answers count from database");
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return answersCount;
	}

	/**
	 * @param questionString
	 * @return Returns the Question object for the given questionString.
	 */
	public Question getQuestionByKeywords(String questionString) {
		Connection connection = null;
		Question question = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		String selectSQLQuery = "SELECT * FROM Questions WHERE question_name = ?";
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(selectSQLQuery);
			prepareStatement.setString(1, questionString);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				String questionName = resultSet.getString(QUESTION_NAME);
				String description = resultSet.getString(QUESTION_DESCRIPTION);
				Integer questionId = resultSet.getInt(QUESTION_ID);
				question = new Question(questionId, questionName, description);
			}
		} catch (DBException | SQLException e) {
			throw new DBException(CAN_T_GET_QUESTION_FROM_DATABASE);
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return question;
	}

	/**
	 * @param keywords
	 * @return Returns the text search query for the given question.
	 */
	public static String getTextSearchQuery(String keywords) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String textSearchQuery = "";

		String sql = "select plainto_tsquery('" + keywords + "')";

		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				textSearchQuery = resultSet.getString(1);
			}
		} catch (DBException | SQLException e) {
			throw new DBException("Can't get text search from database");
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return textSearchQuery;
	}

	/**
	 * @param question
	 * @return Returns the list of related questions for the given question
	 *         keywords.
	 */
	public List<Question> getRelatedQuestions(String question) {
		String textSearchQuery = QuestionDAO.getTextSearchQuery(question);
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		List<Question> questionList = new ArrayList<>();
		String searchingKeywords = textSearchQuery.replace("'", "").replace("&", "|");
		String sql = "select question_name, question_id, question_description, ts_rank(Questions.question_words, to_tsquery('"
				+ searchingKeywords + "')) as rank " + "from Questions where Questions.question_words @@ to_tsquery('"
				+ searchingKeywords + "') order by rank desc";
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				String questionName = resultSet.getString(QUESTION_NAME);
				String description = resultSet.getString(QUESTION_DESCRIPTION);
				Integer questionId = resultSet.getInt(QUESTION_ID);
				Question questionObject = new Question(questionId, questionName, description);
				questionList.add(questionObject);
			}
		} catch (DBException | SQLException e) {
			throw new DBException("Can't get questions from database");
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return questionList;
	}
}
