package in.ramkumar.dto;

public class TopicReportDTO {
	private Integer topicId;
	private Integer questionId;
	private String questionName;
	private String questionDescription;
	private Integer answersCount;

	public TopicReportDTO(Integer topicId, Integer questionId, String questionName,
			String questionDescription, Integer answersCount) {
		this.topicId = topicId;
		this.questionId = questionId;
		this.questionName = questionName;
		this.questionDescription = questionDescription;
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
		return "TopicReportDTO [topicId=" + topicId + ", questionId=" + questionId
				+ ", questionName=" + questionName + ", questionDescription=" + questionDescription
				+ ", answersCount=" + answersCount + "]";
	}
}
