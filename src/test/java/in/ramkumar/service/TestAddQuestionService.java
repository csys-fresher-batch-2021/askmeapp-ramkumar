package in.ramkumar.service;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import in.ramkumar.exception.ServiceException;
import in.ramkumar.model.Question;

class TestAddQuestionService {

	private String validQuestion = "What is Java?";
	private String validDescription = "Help me to answer for this to talk about it in interview";

	private String questionWithGreaterThan300Letters = "What is meant by Java? Why we use getters and setters? "
			+ "What is meant by inheritance? What is method overriding? What is method overloading? "
			+ "What is the use of default keyword in Java? What is meant by polymorphism? Why we "
			+ "static keyword? What is meant by Java? Why we use getters and setters? Usage of polymorphism";

	private String descriptionWithGreaterThan600Letters = "What is meant by Java? Why we use getters and setters? "
			+ "What is meant by inheritance? What is method overriding? What is method overloading? "
			+ "What is the use of default keyword in Java? What is meant by polymorphism? Why we "
			+ "static keyword? What is meant by Java? Why we use getters and setters? Usage of polymorphism "
			+ "What is meant by Java? Why we use getters and setters?"
			+ "What is meant by inheritance? What is method overriding? What is method overloading?"
			+ "What is the use of default keyword in Java? What is meant by polymorphism? Why we "
			+ "static keyword? What is meant by Java? Why we use getters and setters? Usage of polymorphism?";

	/**
	 * Validation with valid question and valid description.
	 */
	@ParameterizedTest
	@CsvSource({
		"What is Java?, Help me to answer for this to talk about it in interview"
	})
	void testAddQuestionWithValidInputs(String questionString, String desString) {
		Question question = new Question();
		question.setQuestionName(questionString);
		question.setDescription(desString);
		QuestionService questionService = new QuestionService();
		try {
			questionService.addQuestion(question);
		} catch (ServiceException e) {
			fail();
		}
	}

	/**
	 * Validation with valid question and null description.
	 */
	@ParameterizedTest
	@CsvSource({
		", What is meant by Java?, Null value not accepted",
		"'', '', Empty value not accepted"
		})
	void testAddQuestionWithInValidInputs(String questioString, String answerString, String message) {
		Question question = new Question();
		question.setQuestionName(questioString);
		question.setDescription(answerString);
		QuestionService questionService = new QuestionService();
		try {
			questionService.addQuestion(question);
		} catch (ServiceException e) {
			assertEquals(message, e.getMessage());
		}
	}

	/**
	 * Validation of question with >300 letters and description with >600 letters
	 */
	@Test
	void testAddQuestionWithGreaterThan300() {
		Question question = new Question();
		question.setQuestionName(questionWithGreaterThan300Letters);
		question.setDescription(validDescription);
		QuestionService questionService = new QuestionService();
		try {
			questionService.addQuestion(question);
		} catch (ServiceException e) {
			String message = e.getMessage();
			assertEquals("Questoin length can't be 300", message);
		}
	}

	/**
	 * Validation of question with description with >600 letters
	 */
	@Test
	void testAddDescriptionWithGreaterThan600Letters() {
		Question question = new Question();
		question.setQuestionName(validQuestion);
		question.setDescription(descriptionWithGreaterThan600Letters);
		QuestionService questionService = new QuestionService();
		try {
			questionService.addQuestion(question);
		} catch (ServiceException e) {
			String message = e.getMessage();
			assertEquals("Description length can't be 600", message);
		}
	}
}
