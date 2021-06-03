package in.ramkumar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.ramkumar.dto.TopicReportDTO;
import in.ramkumar.exception.DBException;
import in.ramkumar.model.Topic;
import in.ramkumar.util.ConnectionUtil;

public class TopicDAO {

	private static final String TOPIC_FOLLOWERS_COUNT = "topic_followers_count";
	private static final String TOPIC_ID = "topic_id";
	private static final String TOPIC_NAME = "topic_name";
	private static final String CAN_T_GET_TOPIC_FROM_DATABASE = "Can't get topic from database";

	/**
	 * This method adds the topic to the database.
	 * 
	 * @param topic
	 */
	public void addTopic(Topic topic) {
		String insertSQLQuery = "INSERT INTO Topics (topic_name, topic_words) VALUES (?, to_tsvector('"
				+ topic.getTopicName() + "'))";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(insertSQLQuery);
			prepareStatement.setString(1, topic.getTopicName());
			prepareStatement.executeUpdate();
		} catch (DBException | SQLException e) {
			throw new DBException("Can't add topic to database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}
	}

	/**
	 * This method gives the all topics available in database.
	 * 
	 * @return List of topics.
	 */
	public List<Topic> getAllTopics() {
		List<Topic> topicList = new ArrayList<>();

		String selectSQLQuery = "SELECT * FROM Topics";

		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(selectSQLQuery);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				String topicName = resultSet.getString(TOPIC_NAME);
				Integer topicId = resultSet.getInt(TOPIC_ID);
				Topic topic = new Topic(topicId, topicName);
				topicList.add(topic);
			}
		} catch (DBException | SQLException e) {
			throw new DBException("Can't get topics from database");
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return topicList;
	}

	/**
	 * @param topicId
	 * @return Returns the Topic object for the given topicId.
	 */
	public Topic getTopicById(Integer topicId) {
		Topic topic = null;
		String selectSQLQuery = "SELECT count(*) topic_followers_count, t.topic_name from Topics t "
				+ " inner join TopicFollowers tf on t.topic_id = tf.topic_id where t.topic_id = ?"
				+ " group by(t.topic_id)";

		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(selectSQLQuery);
			prepareStatement.setInt(1, topicId);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				Integer topicFollowersCount = resultSet.getInt(TOPIC_FOLLOWERS_COUNT);
				String topicName = resultSet.getString(TOPIC_NAME);
				topic = new Topic(topicId, topicName, topicFollowersCount);
			}
		} catch (SQLException | DBException e) {
			throw new DBException(CAN_T_GET_TOPIC_FROM_DATABASE);
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return topic;
	}

	/**
	 * @param topicName
	 * @return Returns the Topic object for the given topicName.
	 */
	public Topic getTopicByName(String topicName) {
		Topic topic = null;
		String selectSQLQuery = "select t.topic_id, count(*) topic_followers_count, t.topic_name from Topics t"
				+ " inner join TopicFollowers tf on t.topic_id = tf.topic_id "
				+ " where t.topic_name ilike ? group by(t.topic_id);";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(selectSQLQuery);
			prepareStatement.setString(1, topicName);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				Integer topicFollowersCount = resultSet.getInt(TOPIC_FOLLOWERS_COUNT);
				Integer topicId = resultSet.getInt(TOPIC_ID);
				topic = new Topic(topicId, topicName, topicFollowersCount);
			}
		} catch (SQLException | DBException e) {
			throw new DBException(CAN_T_GET_TOPIC_FROM_DATABASE);
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return topic;
	}

	/**
	 * @param keywords
	 * @return Returns all the related topics by the given keywords.
	 */
	public List<Topic> getRelatedTopicsByKeywords(String keywords) {
		String textSearchQuery = QuestionDAO.getTextSearchQuery(keywords);
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		List<Topic> suggestedTopics = new ArrayList<>();
		String searchingKeywords = textSearchQuery.replace("'", "").replace("&", "|");
		String sql = "select topic_name, topic_id, ts_rank(Topics.topic_words, to_tsquery('" + searchingKeywords
				+ "')) as rank from Topics where Topics.topic_words @@ to_tsquery('" + searchingKeywords
				+ "') order by rank desc limit 4;";
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(sql);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Integer topicId = resultSet.getInt(TOPIC_ID);
				String topicName = resultSet.getString(TOPIC_NAME);
				Topic topic = new Topic(topicId, topicName);
				suggestedTopics.add(topic);
			}
		} catch (DBException | SQLException e) {
			throw new DBException("Can't get topics from database");
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return suggestedTopics;
	}

	/**
	 * Update TopicName. This method changes new topic name.
	 * 
	 * @param topicId
	 * @param newTopicName
	 */
	public void updateTopicName(Integer topicId, String newTopicName) {
		String updateSQLQuery = "UPDATE Topics SET topic_name = ? WHERE topic_id = ?";

		Connection connection = null;
		PreparedStatement prepareStatement = null;

		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(updateSQLQuery);
			prepareStatement.setString(1, newTopicName);
			prepareStatement.setInt(2, topicId);
			prepareStatement.executeUpdate();
		} catch (SQLException | DBException e) {
			throw new DBException("Can't update topic in database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}
	}

	/**
	 * 
	 * @return Returns the list of topics that are most followed by followers.
	 */
	public List<Topic> getUserInterestedTopics() {
		String sqlQuery = "select topic_name,  count(topic_follower_id) as topic_followers_count, Topics.topic_id from Topics"
				+ " inner join TopicFollowers on Topics.topic_id = TopicFollowers.topic_id"
				+ " group by Topics.topic_id order by topic_followers_count desc limit 15;";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		List<Topic> userInterestedTopic = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(sqlQuery);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Integer topicFollowersCount = resultSet.getInt(TOPIC_FOLLOWERS_COUNT);
				Integer topicId = resultSet.getInt(TOPIC_ID);
				String topicName = resultSet.getString(TOPIC_NAME);
				Topic topic = new Topic(topicId, topicName, topicFollowersCount);
				userInterestedTopic.add(topic);
			}
		} catch (SQLException | DBException e) {
			throw new DBException("Can't get user interested topics from database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}
		return userInterestedTopic;
	}

	/**
	 * This method adds the list of user interested topics.
	 * 
	 * @param topicList
	 * @param userId
	 */
	public void addUserInterestedTopics(List<Topic> topicList, Integer userId) {
		String insertSQLQuery = "INSERT INTO TopicFollowers (topic_id, user_id) VALUES (?, ?)";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			for (Topic topic : topicList) {
				prepareStatement = connection.prepareStatement(insertSQLQuery);
				prepareStatement.setInt(1, topic.getTopicId());
				prepareStatement.setInt(2, userId);
				prepareStatement.executeUpdate();
				prepareStatement.close();
			}
		} catch (DBException | SQLException e) {
			throw new DBException("Can't add user interested topics to database");
		} finally {
			ConnectionUtil.close(connection);
		}
	}

	/**
	 * This method adds the list of topics for the given questionId.
	 * 
	 * @param questionId
	 * @param topicList
	 */
	public void addQuestionRelatedTopics(Integer questionId, List<Integer> topicList) {
		String insertSQLQuery = "INSERT INTO QuestionRelatedTopics (question_id, topic_id) VALUES (?, ?)";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			for (Integer topicId : topicList) {
				prepareStatement = connection.prepareStatement(insertSQLQuery);
				prepareStatement.setInt(1, questionId);
				prepareStatement.setInt(2, topicId);
				prepareStatement.executeUpdate();
				prepareStatement.close();
			}
		} catch (DBException | SQLException e) {
			throw new DBException("Can't add question related topics to database");
		} finally {
			ConnectionUtil.close(connection);
		}

	}

	/**
	 * This method allows the user to follow the given topicId
	 * 
	 * @param topicId
	 * @param userId
	 * @return
	 */
	public boolean followTopic(Integer topicId, Integer userId) {
		String insertSQLQuery = "INSERT INTO TopicFollowers (topic_id, user_id) VALUES (?, ?)";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int row = 0;
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(insertSQLQuery);
			prepareStatement.setInt(1, topicId);
			prepareStatement.setInt(2, userId);
			row = prepareStatement.executeUpdate();
		} catch (DBException | SQLException e) {
			throw new DBException("Can't add topic followers to database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}

		return row == 1;
	}

	/**
	 * This method allows the user to unfollow the given topicId
	 * 
	 * @param topicId
	 * @param userId
	 * @return
	 */
	public boolean unFollowTopic(Integer topicId, Integer userId) {
		String deleteSQLQuery = "DELETE from TopicFollowers where topic_id = ? AND user_id = ?";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		int row = 0;
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(deleteSQLQuery);
			prepareStatement.setInt(1, topicId);
			prepareStatement.setInt(2, userId);
			row = prepareStatement.executeUpdate();
		} catch (DBException | SQLException e) {
			throw new DBException("Can't delete topic followers from database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}

		return row == 1;
	}

	/**
	 * This method checks the user is following the topic or not.
	 * 
	 * @param topicId
	 * @param userId
	 * @return Returns true if the user already following the given topicId,
	 *         otherwise false.
	 */
	public boolean isUserFollowingTopic(Integer topicId, Integer userId) {
		String selectSQLQuery = "SELECT * from TopicFollowers where topic_id = ? AND user_id = ?";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		boolean isUserFollowed = false;
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(selectSQLQuery);
			prepareStatement.setInt(1, topicId);
			prepareStatement.setInt(2, userId);
			ResultSet rs = prepareStatement.executeQuery();
			isUserFollowed = rs.next();
		} catch (DBException | SQLException e) {
			throw new DBException("Can't get topic followers from database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}
		return isUserFollowed;
	}

	/**
	 * This method returns list of topic report for the given topicId
	 * 
	 * @param topicId
	 * @return
	 */
	public List<TopicReportDTO> getTopicReports(Integer topicId) {
		String sqlQuery = "select q.question_name, q.question_id, q.question_description, count(*) answers_count, "
				+ " qrt.question_related_topic_id, t.topic_name from Questions q inner join Answers a on a.question_id = q.question_id "
				+ " inner join QuestionRelatedTopics qrt on q.question_id = qrt.question_id "
				+ " inner join Topics t on t.topic_id = qrt.topic_id where t.topic_id = ? group by (q.question_id, t.topic_name, qrt.question_related_topic_id);";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		List<TopicReportDTO> topicReports = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, topicId);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				String questionName = resultSet.getString("question_name");
				Integer questionId = resultSet.getInt("question_id");
				String questionDescription = resultSet.getString("question_description");
				Integer answersCount = resultSet.getInt("answers_count");
				Integer questionRelatedTopicId = resultSet.getInt("question_related_topic_id");
				String topicName = resultSet.getString(TOPIC_NAME);
				TopicReportDTO topicReportDTO = new TopicReportDTO(topicId, topicName, questionId, questionName,
						questionDescription, questionRelatedTopicId, answersCount);
				topicReports.add(topicReportDTO);
			}
		} catch (SQLException | DBException e) {
			throw new DBException("Can't get topic reports from database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}
		return topicReports;

	}

	/**
	 * This method returns the list of topics that are followed by the given userId
	 * 
	 * @param userId
	 * @return
	 */
	public List<Topic> getUserFollowingTopics(Integer userId) {
		String sqlQuery = "select t.topic_name, t.topic_id, (select count(*) from TopicFollowers tfs where tfs.topic_id = t.topic_id) followers "
				+ " from Topics t where t.topic_id in (select topic_id from TopicFollowers where user_id = ?) order by followers desc;";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		List<Topic> userFollowingTopics = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(sqlQuery);
			prepareStatement.setInt(1, userId);
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Integer topicFollowersCount = resultSet.getInt("followers");
				Integer topicId = resultSet.getInt(TOPIC_ID);
				String topicName = resultSet.getString(TOPIC_NAME);
				Topic topic = new Topic(topicId, topicName, topicFollowersCount);
				userFollowingTopics.add(topic);
			}
		} catch (SQLException | DBException e) {
			throw new DBException("Can't get user following topics from database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}
		return userFollowingTopics;

	}
}
