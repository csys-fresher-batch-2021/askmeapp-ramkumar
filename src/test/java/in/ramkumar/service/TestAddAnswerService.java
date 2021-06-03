package in.ramkumar.service;

import static org.junit.Assert.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import in.ramkumar.exception.ServiceException;
import in.ramkumar.model.Answer;
import in.ramkumar.model.Question;

class TestAddAnswerService {


	/**
	 * Testing with valid answer.
	 */
	@ParameterizedTest
	@ValueSource(strings = {"Java is a independent programming language"})
	void testAddAnswerWithValidInputs(String answers) {
		Question question = new Question();
		question.setQuestionName("What is Java?");
		question.setDescription("What is meant by Java?");
		QuestionService questionService = new QuestionService();
		Answer answer = new Answer();
		answer.setAnswerName(answers);
		AnswerService answerService = new AnswerService();
		try {
			questionService.addQuestion(question, Integer.valueOf(1));
			answerService.addAnswer(Integer.valueOf(1), Integer.valueOf(1), answer);
		} catch (ServiceException e) {
			fail();
		}
	}

	/**
	 * Testing with invalid answers
	 */
	@ParameterizedTest
	@CsvSource({
		"What is java?, '', Empty value not accepted", 
		"What is jsp?, , Null value not accepted"
		})
	void testAddAnswerWithInvalidInputs(String questionString, String answerString, String message) {
		Question question = new Question();
		question.setQuestionName(questionString);
		QuestionService questionService = new QuestionService();
		Answer answer = new Answer();
		answer.setAnswerName(answerString);
		AnswerService answerService = new AnswerService();
		try {
			questionService.addQuestion(question, Integer.valueOf(1));
			answerService.addAnswer(Integer.valueOf(1), Integer.valueOf(1), answer);
		} catch (ServiceException e) {
			assertEquals(message, e.getMessage());
		}
	}
}
