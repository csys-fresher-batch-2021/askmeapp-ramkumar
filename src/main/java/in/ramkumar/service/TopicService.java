package in.ramkumar.service;

import java.util.List;

import in.ramkumar.dao.TopicDAO;
import in.ramkumar.exception.DBException;
import in.ramkumar.exception.ServiceException;
import in.ramkumar.exception.ValidationException;
import in.ramkumar.model.Topic;
import in.ramkumar.validator.NumberValidator;
import in.ramkumar.validator.StringValidator;

public class TopicService {

	private final TopicDAO topicDAO = new TopicDAO();

	/**
	 * This method adds the given topic.
	 * 
	 * @param topicObject
	 */
	public void addTopic(Topic topicObject) {
		Topic topic = null;
		try {
			String topicName = topicObject.getTopicName();
			topic = getTopicByName(topicName);
			if (topic != null) {
				throw new ServiceException("Topic " + topicName + " already exists");
			}
			validateTopic(topicObject);
			topicDAO.addTopic(topicObject);
		} catch (ValidationException | ServiceException e) {
			throw new ServiceException(e.getMessage());
		} catch (DBException e) {
			throw new ServiceException("Unable to add topic");
		}
	}

	/**
	 * @return Returns the topics.
	 */
	public List<Topic> getAllTopics() {
		List<Topic> allTopics;
		try {
			allTopics = topicDAO.getAllTopics();
		} catch (DBException e) {
			throw new ServiceException("Unable to get all topics");
		}
		return allTopics;
	}

	/**
	 * @param topicName
	 * @return Returns the Topic object for the given topic.
	 */
	public Topic getTopicByName(String topicName) {
		Topic topic = null;
		try {
			StringValidator.checkingForNullAndEmpty(topicName);
			topic = topicDAO.getTopicByName(topicName);
		} catch (ValidationException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException("Unable to get topic");
		}
		return topic;
	}

	/**
	 * @param topicName
	 * @return Returns the Topic object for the given topic.
	 */
	public Topic getTopicById(Integer topicId) {
		Topic topic = null;
		try {
			NumberValidator.checkingForNullAndEmpty(topicId);
			topic = topicDAO.getTopicById(topicId);
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		} catch (DBException e) {
			throw new ServiceException("Unable to get topic");
		}
		return topic;
	}


	/**
	 * Validates the given topic.
	 * 
	 * @param topicObject
	 */
	public static void validateTopic(Topic topicObject) {
		if (topicObject == null) {
			throw new ValidationException("Null value not accepted");
		}
		try {
			String topic = topicObject.getTopicName();
			StringValidator.checkingForNullAndEmpty(topic);
		} catch (ValidationException e) {
			throw new ValidationException("Not a valid topic");
		}
	}

	/**
	 * Update topic. This method validates the given new topic. If it is a valid
	 * topic then only it will change the new topic.
	 * 
	 * @param topicId
	 * @param newTopic
	 * @return Returns true if the topic is successfully changed to new topic.
	 */
	public boolean changeTopicName(Integer topicId, String newTopic) {
		Topic topicObject = null;
		try {
			NumberValidator.checkingForNullAndEmpty(topicId);
			StringValidator.checkingForNullAndEmpty(newTopic);
			topicObject = getTopicById(topicId);
			if (topicObject != null) {
				topicDAO.updateTopicName(topicId, newTopic);
			} else {
				throw new ServiceException("Invalid topic id");
			}
		} catch (ValidationException | ServiceException e) {
			throw new ServiceException(e.getMessage());
		} catch (DBException e) {
			throw new ServiceException("Unable to change topic");
		}
		return topicObject != null;
	}

}
