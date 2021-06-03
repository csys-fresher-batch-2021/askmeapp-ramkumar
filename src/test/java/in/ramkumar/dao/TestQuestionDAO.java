package in.ramkumar.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import in.ramkumar.exception.DBException;
import in.ramkumar.model.Question;

public class TestQuestionDAO {

	/**
	 * Testing with valid question.
	 */
	@Test
	public void testAddQuestionDAO() {
		try {
			Question question = new Question();
			question.setQuestionName("What is Javacript?");
			QuestionDAO questionDAO = new QuestionDAO();
			questionDAO.addQuestion(question, Integer.valueOf(1));
		} catch (DBException e) {
			fail("Not yet implemented");
		}
	}

}
