package in.ramkumar.validator;

import in.ramkumar.model.Question;
import static in.ramkumar.util.StringUtil.*;
import static in.ramkumar.validator.StringValidator.*;

public class QuestionValidator {

	private QuestionValidator() {
		// Default constructor
	}

	/**
	 * Question Validation. This method validates the given question. Question
	 * should not be null, empty, and also the length of the question should not be
	 * > 300
	 * 
	 * @param questionObject
	 */
	public static void validateQuestion(Question questionObject) {
		String question = questionObject.getQuestionName();
		Integer numberOfCharactersForQuestion = 300;
		int questionLength = getLength(question);
		if (questionLength < 0 || questionLength > numberOfCharactersForQuestion) {
			throw new IllegalArgumentException("Invalid Question");
		}
	}

	/**
	 * Description Validation. This method validates the given description.
	 * Description should not be null, empty, and also the length of the description
	 * should not be > 600
	 * 
	 * @param questionObject
	 */
	public static void validateDescription(Question questionObject) {
		String description = questionObject.getDescription();
		Integer numberOfCharactersForDescription = 600;
		int descriptionLength = getLength(description);
		if (descriptionLength < 0 || descriptionLength > numberOfCharactersForDescription) {
			throw new IllegalArgumentException("Invalid Description");
		}
	}

}
