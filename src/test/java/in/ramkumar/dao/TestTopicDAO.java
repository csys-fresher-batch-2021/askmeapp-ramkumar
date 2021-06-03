package in.ramkumar.dao;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

import in.ramkumar.exception.DBException;
import in.ramkumar.model.Topic;

class TestTopicDAO {

	/**
	 * Testing add topic with valid input.
	 */
	@Test
	void testAddTopicDAO() {
		try {
			Topic topic = new Topic();
			topic.setTopicName("Java");
			TopicDAO topicDAO = new TopicDAO();
			topicDAO.addTopic(topic);
		} catch (DBException e) {
			fail();
		}
	}
	
	/**
	 * Testing update topic with valid input.
	 */
	@Test
	void testUpdateTopicDAO() {
		try {
			TopicDAO topicDAO = new TopicDAO();
			topicDAO.updateTopicName(11,"PHP");
		} catch (DBException e) {
			e.printStackTrace();
			fail();
		}
	}

}
