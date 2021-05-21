package in.ramkumar.service;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import in.ramkumar.exception.ServiceException;
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
		QuestionService questionService = new QuestionService();
		try {
			questionService.addQuestion(question);
		} catch (ServiceException e) {
			e.printStackTrace();
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
		AnswerService answerService = new AnswerService();
		try {
			answerService.addAnswer(question.getQuestionName(), answer);
		} catch (ServiceException e) {
			fail();
			e.printStackTrace();
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
		AnswerService answerService = new AnswerService();
		try {
			answerService.addAnswer(question.getQuestionName(), answer);
		} catch (ServiceException e) {
			assertEquals("Unable to add answer", e.getMessage());
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
		AnswerService answerService = new AnswerService();
		try {
			answerService.addAnswer(question.getQuestionName(), answer);
		} catch (ServiceException e) {
			assertEquals("Unable to add answer", e.getMessage());
		}
	}

}
