package in.ramkumar.model;

public class Topic {
	
	private Integer topicId;
	private String topicName;

	public Topic() {}
	
	public Topic(Integer topicId, String topicName) {
		this.topicId = topicId;
		this.topicName = topicName;
	}

	/**
	 * @return Returns the topicId.
	 */
	public Integer getTopicId() {
		return topicId;
	}

	/**
	 * This method sets the topicId.
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

	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", topicName=" + topicName + "]";
	}

}
