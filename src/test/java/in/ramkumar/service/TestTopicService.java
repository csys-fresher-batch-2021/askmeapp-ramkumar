package in.ramkumar.service;

import static org.junit.Assert.*;

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
	@CsvSource({
		", Null value not accepted",
		"'', Empty value not accepted",
		"Food, Topic Food already exists",
	})
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
	@CsvSource({
		"1, , Null value not accepted",
		"-1, 'Tech', Invalid topic id",
		"1, '', Empty value not accepted",
	})
	void testChangeTopicWithInValidInputs(Integer topicId, String newTopic, String message) {
		try {
			TopicService topicService = new TopicService();
			topicService.changeTopicName(topicId, newTopic);
		} catch (ServiceException e) {
			assertEquals(message, e.getMessage());
		}
	}
}
