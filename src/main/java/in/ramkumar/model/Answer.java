package in.ramkumar.model;

public class Answer {

	private Integer answerId;
	private String answerName;
	private Integer questionId;
	private Integer userId;

	public Answer() {
	}

	public Answer(Integer answerId, String answerName, Integer questionId, Integer userId) {
		this.answerId = answerId;
		this.answerName = answerName;
		this.questionId = questionId;
		this.userId = userId;
	}

	/**
	 * 
	 * @return answerId
	 */
	public Integer getAnswerId() {
		return answerId;
	}

	/**
	 * Sets the answerId
	 * 
	 * @param answerId
	 */
	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	/**
	 * @return Returns the answer.
	 */
	public String getAnswerName() {
		return answerName;
	}

	/**
	 * This method set the answer.
	 * 
	 * @param answerName
	 */
	public void setAnswerName(String answerName) {
		this.answerName = answerName;
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
	 * 
	 * @param questionId
	 */
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	/**
	 * 
	 * @return userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the userId
	 * 
	 * @param userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * Returns the string representation of for the Answer object.
	 */
	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", answerName=" + answerName + ", questionId=" + questionId
				+ ", userId=" + userId + "]";
	}

}
