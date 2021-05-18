package in.ramkumar.service;

import static org.junit.Assert.*;

import org.junit.Test;

import in.ramkumar.model.Answer;
import in.ramkumar.model.Question;

public class TestAddAnswerService {

	/**
	 * This method adds question.
	 * 
	 * @return Returns the added question object.
	 */
	public Question addQuestion() {
		Question question = new Question();
		question.setQuestionName("What is Java?");
		question.setDescription("What is meant by Java?");
		try {
			QuestionService.addQuestion(question);
		} catch (Exception e) {
			fail();
		}
		return question;
	}

	/**
	 * Testing with valid answer.
	 */
	@Test
	public void testAddAnswer() {
		Question question = addQuestion();
		Answer answer = new Answer();
		answer.setAnswerName("Java is a independent programming language");
		try {
			AnswerService.addAnswer(question.getQuestionName(), answer);
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	/**
	 * Testing with invalid answer(null).
	 */
	@Test
	public void testAddAnswerWithNullAnswer() {
		Question question = addQuestion();
		Answer answer = new Answer();
		answer.setAnswerName(null);
		try {
			AnswerService.addAnswer(question.getQuestionName(), answer);
		} catch (IllegalArgumentException e) {
			assertEquals("Null value not accepted", e.getMessage());
		}
	}

	/**
	 * Testing with invalid answer(empty).
	 */
	@Test
	public void testAddAnswerWithEmptyAnswer() {
		Question question = addQuestion();
		Answer answer = new Answer();
		answer.setAnswerName("");
		try {
			AnswerService.addAnswer(question.getQuestionName(), answer);
		} catch (IllegalArgumentException e) {
			assertEquals("Empty value not accepted", e.getMessage());
		}
	}

}
