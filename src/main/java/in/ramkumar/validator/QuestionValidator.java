package in.ramkumar.validator;

import in.ramkumar.model.Question;
import static in.ramkumar.util.StringUtil.*;
import static in.ramkumar.validator.StringValidator.*;

public class QuestionValidator {
	/**
	 * Question Validation. This method validates the given question. Question
	 * should not be null, empty, and also the length of the question should not be
	 * > 300
	 * 
	 * @param questionObject
	 * @return Returns true iff it is a valid question.
	 */
	public static boolean validateQuestion(Question questionObject) {
		String question = questionObject.getQuestion();
		boolean validQuestion = false;
		Integer numberOfCharactersForQuestion = 300;
		try {
			int questionLength = getLength(question);
			if (checkingForNullAndEmpty(question) && questionLength <= numberOfCharactersForQuestion) {
				validQuestion = true;
			}
		} catch (Exception e) {
			validQuestion = false;
		}
		return validQuestion;
	}

	/**
	 * Description Validation. This method validates the given description.
	 * Description should not be null, empty, and also the length of the description
	 * should not be > 600
	 * 
	 * @param questionObject
	 * @return Returns true iff it is a valid description.
	 */
	public static boolean validateDescription(Question questionObject) {
		boolean validDescription = false;
		String description = questionObject.getDescription();
		Integer numberOfCharactersForDescription = 600;
		try {
			int descriptionLength = getLength(description);
			if (checkingForNullAndEmpty(description) && descriptionLength <= numberOfCharactersForDescription) {
				validDescription = true;
			}
		} catch (Exception e) {
			validDescription = false;
		}
		return validDescription;
	}

}
