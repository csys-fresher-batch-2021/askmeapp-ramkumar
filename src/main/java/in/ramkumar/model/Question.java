package in.ramkumar.model;

import java.util.List;

public class Question {

	private String questionName;
	private String description;
	private List<Answer> answerList;

	/**
	 * @return Returns the answerList.
	 */
	public List<Answer> getAnswerList() {
		return answerList;
	}

	/**
	 * This method sets the answerList.
	 * 
	 * @param answerList
	 */
	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
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

	@Override
	public String toString() {
		return "Question [questionName=" + questionName + ", description=" + description + ", answerList=" + answerList
				+ "]";
	}

}
