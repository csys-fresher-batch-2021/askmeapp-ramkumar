package in.ramkumar.model;

public class Topic {

	private Integer topicId;
	private String topicName;
	private Integer followersCount;

	public Topic() {
	}

	public Topic(Integer topicId, String topicName) {
		this.topicId = topicId;
		this.topicName = topicName;
	}

	public Topic(Integer topicId, String topicName, Integer followersCount) {
		this.topicId = topicId;
		this.topicName = topicName;
		this.followersCount = followersCount;
	}

	/**
	 * @return Returns the topicId.
	 */
	public Integer getTopicId() {
		return topicId;
	}

	/**
	 * This method sets the topicId.
	 * 
	 * @param topicId
	 */
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	/**
	 * @return Returns the topicName
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * This method sets the topicName.
	 * 
	 * @param topicName
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	/**
	 * 
	 * @return followersCount
	 */
	public Integer getFollowersCount() {
		return followersCount;
	}

	/**
	 * Sets the followersCount
	 * 
	 * @param followersCount
	 */
	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}

	/**
	 * Returns the string representation of for the Question object.
	 */
	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", topicName=" + topicName + ", followersCount=" + followersCount + "]";
	}

}
