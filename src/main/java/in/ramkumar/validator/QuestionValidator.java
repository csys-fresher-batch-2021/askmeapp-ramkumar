package in.ramkumar.validator;

import in.ramkumar.model.Question;
import static in.ramkumar.util.StringUtil.*;

import in.ramkumar.exception.UtilException;
import in.ramkumar.exception.ValidationException;

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
		int questionLength;
		try {
			questionLength = getLength(question);
		} catch (UtilException e) {
			throw new ValidationException(e.getMessage());
		}
		if (questionLength > numberOfCharactersForQuestion && questionLength < 0) {
			throw new ValidationException("Questoin length can't be 300");
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
			throw new ValidationException(e.getMessage());
		}
		if (descriptionLength > numberOfCharactersForDescription && descriptionLength > 0) {
			throw new ValidationException("Description length can't be 600");
		}
	}

}
