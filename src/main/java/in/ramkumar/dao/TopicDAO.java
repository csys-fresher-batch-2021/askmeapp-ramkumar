package in.ramkumar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.ramkumar.exception.DBException;
import in.ramkumar.model.Topic;
import in.ramkumar.util.ConnectionUtil;

public class TopicDAO {

	/**
	 * This method adds the topic to the database.
	 * @param topic
	 */
	public void addTopic(Topic topic) {
		String insertSQLQuery = "INSERT INTO Topics (topic_name) VALUES (?)";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(insertSQLQuery);
			prepareStatement.setString(1, topic.getTopicName());
			prepareStatement.executeUpdate();
		} catch (DBException | SQLException e) {
			e.printStackTrace();
			throw new DBException("Can't add topic to database");
		}
		finally {
			ConnectionUtil.close(prepareStatement, connection);
		}
	}

	/**
	 * This method gives the all topics available in database.
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
				String topicName = resultSet.getString("topic_name");
				Integer topicId = resultSet.getInt("topic_id");
				Topic topic = new Topic(topicId, topicName);
				topicList.add(topic);
			}
		} catch (DBException | SQLException e) {
			e.printStackTrace();
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
		String selectSQLQuery = "SELECT * FROM Topics WHERE topic_id = ?";

		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(selectSQLQuery);
			prepareStatement.setInt(1, topicId);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				String topicName = resultSet.getString("topic_name");
				topic = new Topic(topicId, topicName);
			}
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			throw new DBException("Can't get topic from database");
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
		String selectSQLQuery = "SELECT * FROM Topics WHERE topic_name = ?";

		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = ConnectionUtil.getConnection();
			prepareStatement = connection.prepareStatement(selectSQLQuery);
			prepareStatement.setString(1, topicName);
			resultSet = prepareStatement.executeQuery();
			if (resultSet.next()) {
				Integer topicId = resultSet.getInt("topic_id");
				topic = new Topic(topicId, topicName);
			}
		} catch (SQLException | DBException e) {
			e.printStackTrace();
			throw new DBException("Can't get topic from database");
		} finally {
			ConnectionUtil.close(resultSet, prepareStatement, connection);
		}
		return topic;
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
			e.printStackTrace();
			throw new DBException("Can't update topic in database");
		} finally {
			ConnectionUtil.close(prepareStatement, connection);
		}
	}

}
