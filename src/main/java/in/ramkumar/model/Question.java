package in.ramkumar.model;

public class Question {

	private Integer questionId;
	private String questionName;
	private String description;
	private Integer userId;
	private Integer answersCount;

	public Question() {

	}

	public Question(Integer questionId, String questionName, String description, Integer userId, Integer answersCount) {
		this.questionId = questionId;
		this.questionName = questionName;
		this.description = description;
		this.userId = userId;
		this.answersCount = answersCount;
	}

	public Question(String questionName, String description) {
		this.questionName = questionName;
		this.description = description;
	}

	public Question(Integer questionId, String questionName, String description) {
		this.questionId = questionId;
		this.questionName = questionName;
		this.description = description;
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
	 * This method returns the question.
	 * 
	 * @return
	 */
	public String getQuestionName() {
		return questionName;
	}

	/**
	 * This method sets the question for the current Question object.
	 * 
	 * @param question
	 * @return
	 */
	public void setQuestionName(String question) {
		this.questionName = question;
	}

	/**
	 * This method returns the description.
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method sets the description for the current Question object.
	 * 
	 * @param description
	 * @return
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * 
	 * @return answersCount
	 */
	public Integer getAnswersCount() {
		return answersCount;
	}

	/**
	 * Sets the answersCount
	 * 
	 * @param answersCount
	 */
	public void setAnswersCount(Integer answersCount) {
		this.answersCount = answersCount;
	}

	/**
	 * Returns the string representation of for the Question object.
	 */
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionName=" + questionName + ", description=" + description
				+ ", userId=" + userId + ", answersCount=" + answersCount + "]";
	}

}
