package in.ramkumar.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import in.ramkumar.exception.DBException;
import in.ramkumar.model.Question;
import in.ramkumar.service.QuestionService;

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
			questionDAO.addQuestion(question);
		} catch (DBException e) {
			e.printStackTrace();
			fail("Not yet implemented");
		}
	}

}
