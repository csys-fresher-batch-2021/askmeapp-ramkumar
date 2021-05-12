package in.ramkumar.model;

public class Question {
	
	private String questionName;
	private String description;
	
	/**
	 * This method returns the question.
	 * @return
	 */
	public String getQuestionName() {
		return questionName;
	}
	
	/**
	 * This method sets the question for the current Question object.
	 * @param question
	 * @return
	 */
	public void setQuestionName(String question) {
		this.questionName = question;
	}
	
	/**
	 * This method returns the description.
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * This method sets the description for the current Question object.
	 * @param description
	 * @return
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return Returns the string representation of Question object.
	 */
	@Override
	public String toString() {
		return "Question [question=" + questionName + ", description=" + description + "]";
	}
	
}
