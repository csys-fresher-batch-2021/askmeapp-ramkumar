package in.ramkumar.service;

import java.util.ArrayList;
import java.util.List;

import in.ramkumar.model.Question;
import in.ramkumar.validator.QuestionValidator;

public class QuestionService {

	private static final List<Question> questionList = new ArrayList<>();
	
	private QuestionService() {
		//Default constructor
	}

	/**
	 * Add Question. This method validates the question, if it is a valid question
	 * then it will be added to questionList.
	 * 
	 * @param question
	 * @return Returns true iff it is a valid Question object.
	 */
	public static void addQuestion(Question question) {
		QuestionValidator.validateQuestion(question);
		QuestionValidator.validateDescription(question);
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

}
