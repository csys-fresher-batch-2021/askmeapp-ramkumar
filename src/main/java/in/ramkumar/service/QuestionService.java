package in.ramkumar.service;

import java.util.ArrayList;
import java.util.List;

import in.ramkumar.model.Question;
import in.ramkumar.validator.QuestionValidator;
import in.ramkumar.validator.StringValidator;

public class QuestionService {

	private static List<Question> questionList = new ArrayList<>();

	private QuestionService() {
		// Default constructor.
	}

	/**
	 * Add Question. This method validates the question, if it is a valid question
	 * then it will be added to questionList.
	 * 
	 * @param question
	 */
	public static void addQuestion(Question question) {
		QuestionValidator.validateQuestion(question);
		String description = question.getDescription();
		if (description != null && !description.trim().equals("")) {
			QuestionValidator.validateDescription(question);
		}
		questionList.add(question);
	}

	/**
	 * @return Returns list of questions.
	 */
	public static List<Question> getAllQuestions() {
		return questionList;
	}

	/**
	 * @return Returns number of questions in the question list.
	 */
	public static int getNumberOfQuestion() {
		return questionList.size();
	}

	/**
	 * Validates the given string.
	 * 
	 * @param questionName
	 * @return Returns the question index for the given question.
	 */
	public static int getQuestionIndexWithQustionName(String questionName) {
		int questionIndex = -1; // There is no question.
		StringValidator.checkingForNullAndEmpty(questionName);
		for (Question question : questionList) {
			if (question.getQuestionName().equals(questionName)) {
				questionIndex = questionList.indexOf(question);
				break;
			}
		}
		return questionIndex;
	}

	/**
	 * @param questionName
	 * @return Returns the Question object for the given question.
	 */
	public static Question getQuestion(String questionName) {
		int questionIndex = getQuestionIndexWithQustionName(questionName);
		return questionList.get(questionIndex);
	}

}
