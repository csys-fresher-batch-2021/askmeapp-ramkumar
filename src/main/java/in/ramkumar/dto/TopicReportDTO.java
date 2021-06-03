package in.ramkumar.dto;

public class TopicReportDTO {
	private Integer topicId;
	private String topicName;
	private Integer questionId;
	private String questionName;
	private String questionDescription;
	private Integer questionRelatedTopicId;
	private Integer answersCount;

	public TopicReportDTO(Integer topicId, String topicName, Integer questionId, String questionName,
			String questionDescription, Integer questionRelatedTopicId, Integer answersCount) {
		this.topicName = topicName;
		this.topicId = topicId;
		this.questionId = questionId;
		this.questionName = questionName;
		this.questionDescription = questionDescription;
		this.questionRelatedTopicId = questionRelatedTopicId;
		this.answersCount = answersCount;
	}

	public TopicReportDTO() {
	}

	/**
	 * 
	 * @return Returns topicId
	 */
	public Integer getTopicId() {
		return topicId;
	}

	/**
	 * Sets the topicId
	 * @param topicId
	 */
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	/**
	 * 
	 * @return Returns topicName
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * Sets the topicName
	 * @param topicName
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
	/**
	 * 
	 * @return questionId
	 */
	public Integer getQuestionId() {
		return questionId;
	}

	/**
	 * Sets the questionId
	 * @param questionId
	 */
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	/**
	 * 
	 * @return questionName
	 */
	public String getQuestionName() {
		return questionName;
	}

	/**
	 * Sets the questionName
	 * @param questionName
	 */
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	/**
	 * 
	 * @return questionDescription
	 */
	public String getQuestionDescription() {
		return questionDescription;
	}

	/**
	 * Sets the question description
	 * 
	 * @param questionDescription
	 */
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

	/**
	 * 
	 * @return Returns the question related topic id
	 */
	public Integer getQuestionRelatedTopicId() {
		return questionRelatedTopicId;
	}

	/**
	 * sets the question related topic id
	 * 
	 * @param questionRelatedTopicId
	 */
	public void setQuestionRelatedTopicId(Integer questionRelatedTopicId) {
		this.questionRelatedTopicId = questionRelatedTopicId;
	}

	/**
	 * 
	 * @return Returns the answers count
	 */
	public Integer getAnswersCount() {
		return answersCount;
	}

	/**
	 * Sets the answer count
	 * 
	 * @param answersCount
	 */
	public void setAnswersCount(Integer answersCount) {
		this.answersCount = answersCount;
	}

	/**
	 * Returns the string representation of for the TopicReportDTO object.
	 */
	@Override
	public String toString() {
		return "TopicReportDTO [topicId=" + topicId + ", topicName=" + topicName + ", questionId=" + questionId
				+ ", questionName=" + questionName + ", questionDescription=" + questionDescription
				+ ", questionRelatedTopicId=" + questionRelatedTopicId + ", answersCount=" + answersCount + "]";
	}
}
