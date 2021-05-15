package in.ramkumar.model;

public class Answer {
	private String answerName;

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
	 * Returns the string representation of Answer object.
	 */
	@Override
	public String toString() {
		return "Answer [answerName=" + answerName + "]";
	}

}
