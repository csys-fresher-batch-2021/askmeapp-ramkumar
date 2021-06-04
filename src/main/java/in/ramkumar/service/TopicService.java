package in.ramkumar.service;

import java.util.ArrayList;
import java.util.List;

import in.ramkumar.dao.TopicDAO;
import in.ramkumar.dto.TopicReportDTO;
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
			throw new ServiceException(e.getMessage());
		} catch (DBException e) {
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
	 * @return Returns list of related questions.
	 */
	public List<Topic> getRelatedTopics(String keywords) {
		List<Topic> topicList = null;
		try {
			topicList = topicDAO.getRelatedTopicsByKeywords(keywords);
		} catch (DBException e) {
			throw new ServiceException("Unable to get questions");
		}
		return topicList;
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

	/**
	 * This method adds the user interested topics for the given userId.
	 * 
	 * @param topicNameList
	 * @param userId
	 */
	public void addUserInterestedTopics(String[] topicNameList, Integer userId) {
		List<Topic> topicList = new ArrayList<>();
		for (String topicName : topicNameList) {
			Topic topic = getTopicByName(topicName);
			topicList.add(topic);
		}
		try {
			NumberValidator.checkingForNullAndEmpty(userId);
			topicDAO.addUserInterestedTopics(topicList, userId);
		} catch (DBException e) {
			throw new ServiceException("Unable to add user interested topics");
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 
	 * @return Returns the list of topics that are most followed by followers.
	 */
	public List<Topic> getUserInterestedTopics() {
		List<Topic> userInterestedTopics;
		try {
			Integer topicsCountFromTopicFollowers = getTopicsCountFromTopicFollowers();
			if (topicsCountFromTopicFollowers >= 15) {
				userInterestedTopics = topicDAO.getUserInterestedTopics();
			} else {
				userInterestedTopics = topicDAO.getTopicsForNewUsers();
			}
		} catch (DBException e) {
			throw new ServiceException("Unable to get user interested topics");
		}
		return userInterestedTopics;
	}

	/**
	 * This method adds the list of topics for the given questionId.
	 * 
	 * @param questionId
	 * @param questionRelatedTopics
	 */
	public void addQuestionRelatedTopics(Integer questionId, String[] questionRelatedTopics) {
		List<Integer> topicList = new ArrayList<>();
		for (String topicName : questionRelatedTopics) {
			Topic topic = getTopicByName(topicName);
			topicList.add(topic.getTopicId());
		}
		try {
			NumberValidator.checkingForNullAndEmpty(questionId);
			topicDAO.addQuestionRelatedTopics(questionId, topicList);
		} catch (DBException e) {
			throw new ServiceException("Unable to add question related topics");
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
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
		boolean isUserFollowed = false;
		try {
			NumberValidator.checkingForNullAndEmpty(userId);
			NumberValidator.checkingForNullAndEmpty(topicId);
			isUserFollowed = topicDAO.followTopic(topicId, userId);
		} catch (DBException e) {
			throw new ServiceException("Unable to follow topic");
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
		return isUserFollowed;
	}

	/**
	 * This method allows the user to unfollow the given topicId
	 * 
	 * @param topicId
	 * @param userId
	 * @return
	 */
	public boolean unFollowTopic(Integer topicId, Integer userId) {
		boolean isUserUnfollowed = false;
		try {
			NumberValidator.checkingForNullAndEmpty(userId);
			NumberValidator.checkingForNullAndEmpty(topicId);
			isUserUnfollowed = topicDAO.unFollowTopic(topicId, userId);
		} catch (DBException e) {
			throw new ServiceException("Unable to unfollow topic");
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
		return isUserUnfollowed;
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
		boolean isUserFollowing = false;
		try {
			NumberValidator.checkingForNullAndEmpty(userId);
			NumberValidator.checkingForNullAndEmpty(topicId);
			isUserFollowing = topicDAO.isUserFollowingTopic(topicId, userId);
		} catch (DBException e) {
			throw new ServiceException("Unable to get user followed topic");
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
		return isUserFollowing;
	}

	/**
	 * This method returns list of topic report for the given topicId
	 * 
	 * @param topicId
	 * @return
	 */
	public List<TopicReportDTO> getTopicReports(Integer topicId) {
		List<TopicReportDTO> topicReports;
		try {
			NumberValidator.checkingForNullAndEmpty(topicId);
			topicReports = topicDAO.getTopicReports(topicId);
		} catch (DBException e) {
			throw new ServiceException("Unable to get topic reoprts");
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
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
		List<Topic> userFollowingTopics;
		try {
			userFollowingTopics = topicDAO.getUserFollowingTopics(userId);
		} catch (DBException e) {
			throw new ServiceException("Unable to get user following topics");
		}
		return userFollowingTopics;
	}

	/**
	 * 
	 * @param topicId
	 * @return Returns the followers count for the given topic.
	 */
	public Integer getFollowersCount(Integer topicId) {
		Integer followersCount = 0;
		try {
			NumberValidator.checkingForNullAndEmpty(topicId);
			followersCount = topicDAO.getFollowersCount(topicId);
		} catch (DBException e) {
			throw new ServiceException("Unable to get followers count");
		} catch (ValidationException e) {
			throw new ServiceException(e.getMessage());
		}
		return followersCount;
	}

	/**
	 * 
	 * @return Returns the topics count from Topic followers.
	 */
	public Integer getTopicsCountFromTopicFollowers() {
		Integer topicsCount = 0;
		try {
			topicsCount = topicDAO.getTopicsCountFromTopicFollowers();
		} catch (DBException e) {
			throw new ServiceException("Unable to get followers count");
		}
		return topicsCount;
	}

}
