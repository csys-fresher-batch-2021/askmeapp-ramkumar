package in.ramkumar.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import in.ramkumar.exception.ServiceException;
import in.ramkumar.model.Topic;

class TestTopicService {

	/**
	 * Testing with valid topic.
	 */
	@Test
	void testAddTopicWithValidInputs() {
		try {
			TopicService topicService = new TopicService();
			Topic topic = new Topic();
			topic.setTopicName("Servlet JSP");
			topicService.addTopic(topic);
		} catch (ServiceException e) {
			fail("Not yet implemented");
		}
	}

	/**
	 * Testing with invalid inputs.
	 */
	@ParameterizedTest
	@CsvSource({ ", Null value not accepted", "'', Empty value not accepted", "Food, Topic Food already exists", })
	void testAddTopicWithInvalidInputs(String topicName, String message) {
		try {
			TopicService topicService = new TopicService();
			Topic topic = new Topic();
			topic.setTopicName(topicName);
			topicService.addTopic(topic);
		} catch (ServiceException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testing with valid topic to update topic.
	 */
	@Test
	void testChangeTopicWithValidInputs() {
		try {
			TopicService topicService = new TopicService();
			topicService.changeTopicName(11, "PHP programming");
		} catch (ServiceException e) {
			fail();
		}
	}

	/**
	 * Testing with invalid topic.
	 */
	@ParameterizedTest
	@CsvSource({ "1, , Null value not accepted", "-1, 'Tech', Invalid topic id", "1, '', Empty value not accepted", })
	void testChangeTopicWithInValidInputs(Integer topicId, String newTopic, String message) {
		try {
			TopicService topicService = new TopicService();
			topicService.changeTopicName(topicId, newTopic);
		} catch (ServiceException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testing with valid topicId.
	 */
	@Test
	void testGetTopicByIdWithValidInputs() {
		try {
			TopicService topicService = new TopicService();
			Topic topic = topicService.getTopicById(Integer.valueOf(1));
			assertNotNull(topic);
		} catch (ServiceException e) {
			fail();
		}
	}

	/**
	 * Testing with invalid topicId.
	 */
	@ParameterizedTest
	@CsvSource({ "0", "-2" })
	void testGetTopicByIdWithInvalidInputs(Integer topicId) {
		try {
			TopicService topicService = new TopicService();
			Topic topic = topicService.getTopicById(topicId);
			assertNull(topic);
		} catch (ServiceException e) {
			fail();
		}
	}

	/**
	 * Testing with valid topicId.
	 */
	@Test
	void testGetTopicByWithNameValidInputs() {
		try {
			TopicService topicService = new TopicService();
			Topic topic = topicService.getTopicByName("Technology");
			assertNotNull(topic);
		} catch (ServiceException e) {
			fail();
		}
	}

	/**
	 * Testing with invalid topic names.
	 */
	@ParameterizedTest
	@CsvSource({ "0", "hello", })
	void testGetTopicByWithNameInvalidInputs(String topicName) {
		try {
			TopicService topicService = new TopicService();
			Topic topic = topicService.getTopicByName(topicName);
			assertNull(topic);
		} catch (ServiceException e) {
			fail();
		}
	}

	/**
	 * Testing with invalid topic names.
	 */
	@ParameterizedTest
	@CsvSource({ ", Null value not accepted", "'', Empty value not accepted" })
	void testGetTopicByWithNameEmptyAndNullValue(String topicName, String message) {
		try {
			TopicService topicService = new TopicService();
			topicService.getTopicByName(topicName);
		} catch (ServiceException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Testing with getting related topics with valid inputs.
	 */
	@Test
	void testRelatedTopicsWithValidInputs() {
		try {
			TopicService topicService = new TopicService();
			List<Topic> relatedTopics = topicService.getRelatedTopics("advice");
			assertTrue(relatedTopics.size() > 0);
		} catch (ServiceException e) {
			fail();
		}
	}

	/**
	 * Testing with getting related topics with invalid inputs.
	 */
	@Test
	void testRelatedTopicsWithInvalidInputs() {
		try {
			TopicService topicService = new TopicService();
			List<Topic> relatedTopics = topicService.getRelatedTopics("money");
			assertTrue(relatedTopics.size() < 1);
		} catch (ServiceException e) {
			fail();
		}
	}
	
	@Test
	void testAddUserInterestedTopics() {
		try {
			String[] topics = {"Technology", "Technology Trends"};
			TopicService topicService = new TopicService();
			topicService.addUserInterestedTopics(topics, Integer.valueOf(10));
		} catch (ServiceException e) {
			fail();
		}
	}
}
