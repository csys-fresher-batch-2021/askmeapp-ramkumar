package in.ramkumar.service;

import static org.junit.Assert.*;

import org.junit.Test;

import in.ramkumar.model.Question;

public class TestAddQuestionService {

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
	@Test
	public void testAddQuestionWithValidQuestionAndDescription() {
		Question question = new Question();
		question.setQuestionName(validQuestion);
		question.setDescription(validDescription);
		try {
			QuestionService.addQuestion(question);
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Validation with valid question and null description.
	 */
	@Test
	public void testAddQuestionWithValidQuestionAndNullDescription() {
		Question question = new Question();
		question.setQuestionName(validQuestion);
		question.setDescription(null);
		try {
			QuestionService.addQuestion(question);
		} catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Null value not accepted", message);
		}
	}
	
	/**
	 * Validation with null question and valid description.
	 */
	@Test
	public void testAddQuestionWithNullQuestionAndValidDescription() {
		Question question = new Question();
		question.setQuestionName(null);
		question.setDescription(validDescription);
		try {
			QuestionService.addQuestion(question);
		} catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Null value not accepted", message);
		}
	}
	
	/**
	 * Validation with null question and null description.
	 */
	@Test
	public void testAddQuestionWithNullQuestionAndNullDescription() {
		Question question = new Question();
		question.setQuestionName(null);
		question.setDescription(null);
		try {
			QuestionService.addQuestion(question);
		} catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Null value not accepted", message);
		}
	}
	
	/**
	 * Validation with empty question and empty description.
	 */
	@Test
	public void testAddQuestionWithEmptyQuestionAndEmptyDescription() {
		Question question = new Question();
		question.setQuestionName("");
		question.setDescription("");
		try {
			QuestionService.addQuestion(question);
		} catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Empty value not accepted", message);
		}
	}
	
	/**
	 * Validation of question with >300 letters and description with >600 letters
	 */
	@Test
	public void testAddQuestionWithGreaterThan300Letters() {
		Question question = new Question();
		question.setQuestionName(questionWithGreaterThan300Letters);
		question.setDescription(validQuestion);
		try {
			QuestionService.addQuestion(question);
		} catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Invalid Question", message);
		}
	}
	
	/**
	 * Validation of question with description with >600 letters
	 */
	@Test
	public void testAddDescriptionWithGreaterThan600Letters() {
		Question question = new Question();
		question.setQuestionName(validQuestion);
		question.setDescription(descriptionWithGreaterThan600Letters);
		try {
			QuestionService.addQuestion(question);
		} catch (IllegalArgumentException e) {
			String message = e.getMessage();
			assertEquals("Invalid Description", message);
		}
	}

}
