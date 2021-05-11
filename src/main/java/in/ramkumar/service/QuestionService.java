package in.ramkumar.service;

import java.util.ArrayList;
import java.util.List;

import in.ramkumar.model.Question;
import in.ramkumar.validator.QuestionValidator;

public class QuestionService {

	private static List<Question> questionList = new ArrayList<Question>();

	/**
	 * Add Question. This method validates the question, if it is a valid question
	 * then it will be added to questionList.
	 * 
	 * @param question
	 * @return Returns true if it is a valid Question object.
	 */
	public static boolean addQuestion(Question question) {
		boolean validQuestion = false;
		if (QuestionValidator.validateQuestion(question) && QuestionValidator.validateDescription(question)) {
			questionList.add(question);
			validQuestion = true;
		}
		return validQuestion;
	}
	
	/**
	 * 
	 * @return Returns all the questions.
	 */
	public static List<Question> getAllQuestions() {
		return questionList;
	}

}
