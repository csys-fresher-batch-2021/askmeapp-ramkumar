package in.ramkumar.validator;

import in.ramkumar.model.Question;
import static in.ramkumar.util.StringUtil.*;

import in.ramkumar.exception.UtilException;
import in.ramkumar.exception.ValidationException;

public class QuestionValidator {

	private static final String INVALID_DESCRIPTION = "Invalid description";
	private static final String INVALID_QUESTION = "Invalid question";

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
		int questionLength;
		try {
			questionLength = getLength(question);
		} catch (UtilException e) {
			e.printStackTrace();
			throw new ValidationException(INVALID_QUESTION);
		}
		if (questionLength > numberOfCharactersForQuestion && questionLength < 0) {
			throw new ValidationException(INVALID_QUESTION);
		}
	}

	/**
	 * Description Validation. This method validates the given description.
	 * Description should not be null, empty, and also the length of the description
	 * should not be > 600
	 * 
	 * @param questionObject
	 */
	public static void validateDescription(Question questionObject){
		String description = questionObject.getDescription();
		Integer numberOfCharactersForDescription = 600;
		int descriptionLength;
		try {
			descriptionLength = getLength(description);
		} catch (UtilException e) {
			e.printStackTrace();
			throw new ValidationException(INVALID_DESCRIPTION);
		}
		if (descriptionLength > numberOfCharactersForDescription && descriptionLength > 0) {
			throw new ValidationException(INVALID_DESCRIPTION);
		}
	}

}
